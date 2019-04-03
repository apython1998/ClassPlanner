from selenium import webdriver
import time
import json # Use is json.dumps(object)

valid_semesters = ['S', 'F', 'W', 'SUM']
valid_frequency = ['IRR', 'Y', 'E', 'O']


def scrape_department_links(url):
    browser = webdriver.Chrome()
    browser.get(url)

    department_links = []
    atoz_div = browser.find_element_by_id('atozindex')
    atoz_links = atoz_div.find_elements_by_tag_name('a')
    for link in atoz_links:
        href = link.get_attribute('href')
        if href is not None:
            department_links.append(href)
    browser.close()
    return department_links


def scrape_courses(url, courses):
    browser = webdriver.Chrome()
    browser.get(url)
    dept_courses = browser.find_elements_by_class_name('courseblock')
    for course in dept_courses:
        course_json = dict()
        course_title_block = course.find_element_by_class_name('courseblocktitle').text
        course_title_block_array = course_title_block.split()
        course_dept = course_title_block_array[0]
        course_number = course_title_block_array[1]
        course_name = ' '.join(course_title_block_array[2:-1])
        course_desc_block = course.find_element_by_class_name('courseblockdesc').text
        course_credits = course_desc_block.split()[-2]  # TODO: SHOULD 1-3 Credits be broken into 3 different courses with the same info, or can it be a range
        course_semesters_and_frequency = course_desc_block[course_desc_block.rfind('(')+1:course_desc_block.rfind(')')]\
            .replace('-', ' ').replace(',', ' ').split()
        course_semesters = []
        course_frequency = ''
        for data in course_semesters_and_frequency:
            if data in valid_semesters:
                course_semesters.append(data)
            elif data in valid_frequency:
                course_frequency = data
        course_prereqs = []
        course_choose_ones = []
        if 'Prerequisites: ' in course_desc_block:  # If the class has prereqs, try and parse it
            course_prereqs_dirty = course_desc_block.split('Prerequisites: ')[1].strip()
            course_prereqs_dirty = course_prereqs_dirty[:course_prereqs_dirty.find('(')].strip().replace('.', '')
            course_prereqs_dirty = course_prereqs_dirty[0:course_prereqs_dirty.rfind('0')+1].split()
            course_prereqs_parsed = ''
            for i in range(len(course_prereqs_dirty)):
                item = course_prereqs_dirty[i]
                if i is 0:
                    course_prereqs_parsed += item
                elif item.replace(',', '').isnumeric():
                    course_prereqs_parsed += item
                else:
                    course_prereqs_parsed += ' ' + item
            requirements = course_prereqs_parsed.split(';')  # Split into separate requirements by ;
            for requirement in requirements:
                if 'One of the following: ' in requirement:
                    requirement_clean = requirement.replace('One of the following:', '').strip()
                    requirement_split = requirement_clean.split(',')
                    for i in range(len(requirement_split)):
                        requirement_split[i] = requirement_split[i].strip()
                    course_choose_ones.append(requirement_split)
                elif 'or' in requirement:
                    requirement_split = requirement.split('or')
                    for i in range(len(requirement_split)):
                        requirement_split[i] = requirement_split[i].strip()
                    course_choose_ones.append(requirement_split)
                elif 'and' in requirement:
                    requirement_split = requirement.split('and')
                    for i in range(len(requirement_split)):
                        requirement_split[i] = requirement_split[i].strip()
                    for item in requirement_split:
                        if len(item) == 9:
                            course_prereqs.append(item)
                else:
                    if len(requirement) == 9:  # Length of Course DEPT+Number
                        course_prereqs.append(requirement)
        for prereq in course_prereqs:
            if len(prereq) != 9:
                course_prereqs.remove(prereq)
        # Put Data into the Dictionary
        course_json['courseNum'] = course_dept+course_number # Department and Number (DEPT00000)
        course_json['name'] = course_name                    # Name
        course_json['credits'] = course_credits              # Credits (0.0)
        course_json['semestersOffered'] = course_semesters  # Semesters
        course_json['frequencyOffered'] = course_frequency  # Frequency
        course_json['prereqs'] = course_prereqs              # Prereqs [COMP171, COMP172]
        course_json['chooseOnes'] = course_choose_ones      # Choose Ones
        courses.append(course_json)
    browser.close()                                          # Close the browser


def main():
    department_links = scrape_department_links('https://catalog.ithaca.edu/undergrad/coursesaz/')
    courses = []
    for department_link in department_links:
        scrape_courses(department_link, courses)
        time.sleep(1)
    for course in courses:  # For each course, verify the data
        bad_prereqs = []
        bad_choose_ones = []
        for prereq in course['prereqs']:
            if len(prereq) != 9:
                bad_prereqs.append(prereq)
        for choose_one in course['choose_ones']:
            for prereq in choose_one:  # Get rid of bad prereqs in the choose ones
                if len(prereq) != 9:
                    choose_one.remove(prereq)
            if len(choose_one) < 2:    # If the size of the choose one is less than 2 now, get rid of it
                bad_choose_ones.append(choose_one)
    with open('../src/main/resources/course_catalog.json', 'w') as outfile:
        json.dump(courses, outfile)


main()
