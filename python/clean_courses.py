import json

def main():
    with open('../src/main/resources/courseCatalog.json', 'r') as course_JSON:
        course_catalog = json.load(course_JSON)
    clean_courses = []
    for course in course_catalog:
        clean_course = dict()
        clean_course['courseNum'] = course['courseNum'].strip()
        clean_course['name'] = course['name'].strip()
        clean_course['credits'] = course['credits']
        if len(course['semestersOffered']) == 0:
            clean_course['semestersOffered'] = ['F', 'S']
        else:
            clean_course['semestersOffered'] = course['semestersOffered']
        clean_course['frequencyOffered'] = course['frequencyOffered']
        clean_prereqs = []
        for prereq in course['prereqs']:
            clean_prereqs.append(prereq.strip())
        clean_course['prereqs'] = clean_prereqs
        clean_choose_ones = []
        for choose_one in course['chooseOnes']:
            clean_choose_one = []
            for prereq in choose_one:
                clean_choose_one.append(prereq.strip())
            clean_choose_ones.append(clean_choose_one)
        clean_course['chooseOnes'] = clean_choose_ones
        clean_courses.append(clean_course)
    with open('../src/main/resources/courseCatalog.json', 'w') as out_file:
        json.dump(clean_courses, out_file)




main()

