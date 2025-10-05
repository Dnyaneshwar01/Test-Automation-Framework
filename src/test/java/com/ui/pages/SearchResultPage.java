package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BrowserUtility {

    private static final By PRODUCT_LISTING_TITLE_LOCATOR   = By.cssSelector("span[class='lighter']");
    private static final By ALL_PRODUCT_LIST                = By.cssSelector("[class='right-block'] a[itemprop='url']");
    private static final By PRODUCT_NAME                    = By.cssSelector("ul[class='product_list grid row'] li[class*='product']:nth-of-type(2) [class='product-name']");
    private static final By VIEW_PRODUCT                    = By.cssSelector("ul[class='product_list grid row'] li[class*='product']:nth-of-type(2) a[title='View']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultTitle() {
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }

    public boolean isSearchTermPresentInProductList(String searchTerm) {
        List<String> keywordsList = Arrays.asList(searchTerm.toLowerCase().split(" "));
        List<String> productNameList = getAllVisibleText(ALL_PRODUCT_LIST);

        boolean result =  productNameList.stream()
                .anyMatch(name -> (keywordsList.stream().anyMatch((name.toLowerCase()::contains))));
        return result;
    }

    public ProductDetailPage clickOnTheProduct() {
        waitUntilElementDisplay(PRODUCT_NAME);
        mouseHover(PRODUCT_NAME);
        mouseHoverAndClick(VIEW_PRODUCT);
        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        return productDetailPage;
    }
}
