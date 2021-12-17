from selenium import webdriver
import time

from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait

def testSearch():
    driver = webdriver.Chrome('C:/Users/User/PycharmProjects/pythonTest/venv/chromedriver')
    driver.get("https://www.kupibilet.ru/")
    time.sleep(3)
    inputWhere1 = driver.find_element(By.XPATH,"//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[1]/div[1]/input")
    inputWhere1.clear()
    inputWhere1.send_keys("Санкт-Петербург")
    time.sleep(2)
    inputWhere = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[3]/div[1]/input")
    inputWhere.send_keys("Сочи")
    time.sleep(2)
    menu = driver.find_element(By.XPATH, "//*[@id='react-autowhatever-1']/ul")
    menu.click()
    time.sleep(2)
    buttonSearch = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/button")
    buttonSearch.click()
    time.sleep(2)
    dateOut = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[1]/div[1]/input")
    if dateOut.get_attribute('placeholder') == "Укажите дату":
        print("сработало тестирование 1")
    else:
        print("Не сработало 1")
    time.sleep(1)
    driver.quit()

def testSearch2():
    driver = webdriver.Chrome('C:/Users/User/PycharmProjects/pythonTest/venv/chromedriver')
    driver.get("https://www.kupibilet.ru/")
    time.sleep(3)
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    inputEmail = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[6]/div/div/div/div/form/div[1]/div/div/div/input")
    inputEmail.send_keys("123456")
    time.sleep(2)
    buttonSearche = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[6]/div/div/div/div/form/div[1]/div/button")
    buttonSearche.click()
    time.sleep(2)
    out2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[6]/div/div/div/div/form/div[1]/div/div/div/span")
    if out2.text == "Неверный формат электронной почты":
        print("сработало тестироание 2")
    else:
        print("Не сработало 1")
    driver.quit()

def testSearch3():
    driver = webdriver.Chrome('C:/Users/User/PycharmProjects/pythonTest/venv/chromedriver')
    driver.get("https://www.kupibilet.ru/")
    time.sleep(3)

    inputWhere1 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[1]/div[1]/input")
    inputWhere1.clear()
    inputWhere1.send_keys("Санкт-Петербург")
    time.sleep(2)

    inputWhere = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[3]/div[1]/input")
    inputWhere.send_keys("Сочи")
    time.sleep(2)

    menu = driver.find_element(By.XPATH, "//*[@id='react-autowhatever-1']/ul")
    menu.click()
    time.sleep(2)

    dateOut = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[1]/div[1]/input")
    dateOut.click()
    time.sleep(2)

    dateIn = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[1]/div[3]/div/div[2]/div/div/div/div[2]/div/div[3]/div[5]/div[5]")
    dateIn.click()
    time.sleep(2)

    buttonSearch = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/button")
    buttonSearch.click()
    time.sleep(15)

    buttonFilter = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div/div[2]/div/div/button")
    buttonFilter.click()
    time.sleep(5)

    paragraph = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[4]/div/div/div[2]/div/form/section[2]/div/div/label[2]/span[2]/span")
    paragraph.click()
    time.sleep(2)

    paragraph2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[4]/div/div/div[2]/div/form/section[2]/div/div/label[3]/span[2]/span")
    paragraph2.click()
    time.sleep(2)

    buttonShow = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[4]/div/div/div[3]/button")
    buttonShow.click()
    time.sleep(5)

    testText = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div/div[3]/div[1]/div/div[1]/div/div[1]/div[4]/div/div[2]/div/div")
    print(testText.text)

    string = testText.text
    string1, separator, string2 = string.partition(',')
    if string1 == "Прямой":
        print("сработало тестироание 3")
    else:
        print("Не сработало 3")

    driver.quit()

def testSearch4():
    driver = webdriver.Chrome('C:/Users/User/PycharmProjects/pythonTest/venv/chromedriver')
    driver.get("https://www.kupibilet.ru/")
    time.sleep(3)

    inputWhere1 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[1]/div[1]/input")
    inputWhere1.clear()
    inputWhere1.send_keys("Санкт-Петербург")
    time.sleep(2)

    inputWhere2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[1]/div[3]/div[1]/input")
    inputWhere2.send_keys("Сочи")
    time.sleep(2)

    menu = driver.find_element(By.XPATH, "//*[@id='react-autowhatever-1']/ul")
    menu.click()
    time.sleep(2)

    dateOut = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[1]/div[1]/input")
    dateOut.click()
    time.sleep(2)

    dateIn = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[1]/div[3]/div/div[2]/div/div/div/div[2]/div/div[3]/div[5]/div[5]")
    dateIn.click()
    time.sleep(2)

    buttonSearch = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/button")
    buttonSearch.click()
    time.sleep(15)

    testText = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div/div[2]/div/div/div/div[1]/div[1]/div[1]/span")
    time.sleep(2)

    testText2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div/div[3]/div[1]/div/div[1]/div/div[2]/div[1]/button/span")
    string = testText.text
    time.sleep(2)
    string1, separator, string2 = string.partition(' ')
    string3 = testText2.text
    string4, separator1, string5 = string3.partition('за ')
    if string2 == string5:
        print("сработало тестироание 4")
    else:
        print("Не сработало 4")

    driver.quit()

def testSearch5():
    driver = webdriver.Chrome('C:/Users/User/PycharmProjects/pythonTest/venv/chromedriver')
    driver.get("https://www.kupibilet.ru/")
    time.sleep(3)

    inputWhere1 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[1]")
    inputWhere1.click()
    time.sleep(2)

    buttonPlus1 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div/button[2]/span")
    buttonPlus1.click()
    time.sleep(1)
    buttonPlus2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")
    buttonPlus2.click()
    buttonPlus2.click()
    time.sleep(1)
    buttonPlus3 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/button[2]")
    buttonPlus3.click()
    time.sleep(1)

    num1 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/span")
    num2 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/span")
    num3 = driver.find_element(By.XPATH, "//*[@id='app-wrap']/div[2]/div[1]/div[3]/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/span")


    string = inputWhere1.text
    string1, separator, string2 = string.partition(' ')
    string3 = int(num1.text)+int(num2.text)+int(num3.text)
    if int(string1) == string3:
        print("сработало тестироание 5")
    else:
        print("Не сработало 5")



testSearch5()