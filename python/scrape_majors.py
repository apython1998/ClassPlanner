from selenium import webdriver
import time
import re
import json  # Use is json.dumps(object)


def scrape_major(url):                                              # Scrape Data for a Single Major
    major = dict()
    major_title = ''                                                # Title for Major
    major_type = ''                                                 # Type of Major
    major_required_courses = []                                     # Required Classes
    major_required_choose_ones = []                                 # Required ChooseOnes
    if 'sports-media' in url:                                       # The only major that doesn't list its type in URL
        major_title = 'Sports Media'
        major_type = 'BS'
    else:                                                           # Loop works for all other links
        major_url_title_type_section = url.split('/')[-2]           # Extract major title and type from url
        if major_url_title_type_section[0] is '-':                  # Fixes error where url starts with -
            major_url_title_type_section = major_url_title_type_section[1:]
        major_title_type_split = major_url_title_type_section.split('-')
        if 'teaching' in major_title_type_split:                    # Loop for handling majors with a teaching option
            major_type_temp = major_title_type_split[-3]
            major_title_type_split.remove(major_type_temp)
            major_title_type_split.append(major_type_temp)
        major_type = major_title_type_split[-1].upper()             # Get & Capitalize the type of major
        for i in range(len(major_title_type_split[0:-1])):
            major_title_type_split[i] = major_title_type_split[i][0].upper() + major_title_type_split[i][1:]
        major_title = " ".join(major_title_type_split[0:-1])        # Generate the title of the major
    browser = webdriver.Chrome()
    browser.get(url)
    try:
        content_div = browser.find_element_by_id('content')             # div that has the content for requirements
        try:
            requirements_table = content_div.find_elements_by_tag_name('table')[2]
            requirement_rows = requirements_table.find_elements_by_tag_name('tr')
            for requirement_row in requirement_rows:
                row_text = requirement_row.text
                if 'one of the following' in row_text:
                    pass  # Not sure what to do yet
                else:
                    regexp = re.compile('^[A-Z]{4} [0-9]{5}')  # Of the form AAAA 10000
                    result = regexp.search(row_text)
                    if result is not None:
                        reg_string = result.string[result.regs[0][0]:result.regs[0][1]]
                        print(reg_string.replace(" ", ''))
        except:
            print('There are no major requirements listed for {} {}'.format(major_title, major_type))
    except:
        print('There is no site for {} {}'.format(major_title, major_type))
    browser.close()
    major['title'] = major_title.strip()                            # Populate the Dictionary
    major['type'] = major_type.strip()                              # ...
    major['courses'] = major_required_courses                       # ...
    major['grouping'] = major_required_choose_ones                  # ...
    return major                                                    # Return the major


def scrape_major_urls(url):  # Scrape URLs for every Major
    major_urls = []
    browser = webdriver.Chrome()
    browser.get(url)
    pass  # Do shit
    content_div = browser.find_element_by_id('textcontainer')
    major_links = content_div.find_elements_by_tag_name('a')
    for major_link in major_links:
        href = major_link.get_attribute('href')
        if href is not None:
            major_urls.append(href)
    browser.close()
    return major_urls


def main():
    major_urls = scrape_major_urls('https://catalog.ithaca.edu/undergrad/programsaz/undergraduate-degree/')
    majors = []
    for major_url in major_urls[0:1]:
        majors.append(scrape_major(major_url))  # Add a dictionary representing major requirements
        # time.sleep(1)                           # Keep IP from getting blacklisted
    with open('../src/main/resources/majorCatalog.json', 'w') as outfile:
        json.dump(majors, outfile)


main()
