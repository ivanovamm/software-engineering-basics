import { Builder, By, until } from 'selenium-webdriver';
import { expect } from 'chai';
import { describe, it, before, after } from 'mocha';

describe('End-to-End Testing', function() {
    this.timeout(30000);
    let driver;

    before(async function() {
        driver = await new Builder().forBrowser('firefox').build();
    });

    after(async function() {
        await driver.quit();
    });

    it('should load the page and fill the form', async function() {
        await driver.get('http://localhost:8080/index.jsp');

        const xInput = await driver.findElement(By.id('x_value'));
        await xInput.sendKeys('2');

        const yCheckbox = await driver.findElement(By.id('y_value_-2'));
        await yCheckbox.click();

        const rCheckbox = await driver.findElement(By.xpath("//input[@name='optionR' and @value='2']"));
        await rCheckbox.click();

        const checkButton = await driver.findElement(By.id('check'));
        await checkButton.click();

        await driver.wait(until.elementLocated(By.id('table')), 10000);

        const table = await driver.findElement(By.id('table'));
        const rows = await table.findElements(By.tagName('tr'));

        expect(rows.length).to.be.greaterThan(1);

        const firstRowColumns = await rows[1].findElements(By.tagName('td'));
        const xValue = await firstRowColumns[0].getText();
        const yValue = await firstRowColumns[1].getText();
        const rValue = await firstRowColumns[2].getText();

        expect(xValue).to.equal('2');
        expect(yValue).to.equal('-2');
        expect(rValue).to.equal('2');
    });
});
