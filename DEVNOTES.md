## Scraper Details
In the Python directory: <b>
* scrape_courses.py : Scrapes every course listed on the IC course catalog
   * NOTE: Generates courseCatalog.json in resources
* scrape_majors.py : Scrapes every major listed on the IC major catalog (w/ course requirements as strings)
   * NOTE: Emerging Media BS and Clinical Health Studies are duplicated and keeping it this way for now
   * NOTE: Generates majorCatalog.json in resources
* clean_majors.py : Takes every course requirement in each major, and inserts the JSON for that course object
   * NOTE: Run scrape_majors.py first to get the most up to date majors and requirements
   * NOTE: Generates a file majorCatalogWithCourseObjects.json in resources
   
   
## Directory Implementation Notes
* Students are identified by username which is provided at point of registration
* Courses are identified by their DEPT+Number combination
   * Ex.) COMP345000
   * Ex.) TVR12300
* Majors are identified by their Title+Type combination
   * Ex.) Computer Science Major BA
   * Ex.) Computer Science Major BS
   * Ex.) Legalstudies Major BA
   * Ex.) Mathematics Major Teaching Option BA