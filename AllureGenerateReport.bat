xcopy /y /e /k allure-report\html\history\*.* allure-results\history\
libs/allure-2.13.3/bin/allure generate allure-results\ -o allure-report\html --clean
libs/allure-2.13.3/bin/allure open allure-report\html