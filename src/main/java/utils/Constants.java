package utils;

import org.openqa.selenium.By;

public class Constants {
    //config profs
    public static final String BROWSER = "browser";
    public static final String BASEURL = "baseUrl";
    public static final String USERNAME = "user_name";
    public static final String PASSWORD = "password";


    //pages
    public static final String ADD_TO_CART_PAGE = "AddToCartPage";
    public static final String THIRD_DEAL_PAGE = "ThirdDealPage";
    public static final String SEARCH_RESULT_PAGE = "SearchResultsPage";
    public static final String BACK_TO_HOME_PAGE = "BackToHomePage";


    // Locators  no need
    public static final String TODAYSDEAL_LOCATOR = "Today's Deals";
    public static final String DEALS = "deals";
    public static final String TODAYSDEAL_TEXT = "Today's Deals";
    public static final By TODAYSDEAL_BY = By.linkText(TODAYSDEAL_TEXT);
    public static final String LOGIN_URL = "https://www.amazon.in/";

    public static final long DURATION = 15;

    //urls
    public static final String THIRD_PAGE_URL = "https://www.amazon.in/deals?ref_=nav_cs_gb";


}

