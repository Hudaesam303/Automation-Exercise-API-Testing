package Full_User_Exeperince;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import Full_User_Exeperince.ConfigReader;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Automation Exercise API")
@Feature("Products & Search")
public class Products_SearchCross_Validation {
    String baseUrl=ConfigReader.get("base.url");

    @Test(priority = 1)
    @Description("Fetch all products list and verify response contains products array with required fields")
    public void getProductList_TC01()
    {

        Response response=
                given()
                        .baseUri(baseUrl)
                        .when()
                        .get("/api/productsList")
                        .then()
                        .statusCode(200)
                        .body("products",not(empty()))
                        .body("products.id",everyItem(notNullValue()))
                        .body("products.name",everyItem(notNullValue()))
                        .body("products.category",everyItem(notNullValue()))
                        .body("products[0].id",notNullValue())
                        .body("products[3].brand",notNullValue())
                        .extract().response();
        assert response.asString().contains("\"responseCode\": 200");
        System.out.println("TC01_Response: "+response.asString());

    }

    @Test(priority = 2)
    @Description("Search products by keyword 'Top' and verify all results contain the keyword")
    public void searchAboutProduct_TC02(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("search_product", "Top")
                        .when()
                        .post("/api/searchProduct")
                        .then()
                        .body("products.name", everyItem(containsStringIgnoringCase("Top")))
                        .extract().response();

        assert response.asString().contains("\"responseCode\": 200");
        System.out.println("TC02_Response: "+response.asString());



    }


    @Test(priority = 3)
    @Description("Fetch all brands list and verify 8 unique brands including target brand Polo")
    public void getBrandsList_TC03(){
        Response response=
                given()
                        .baseUri(baseUrl)
                .when()
                        .get("/api/brandsList")
                .then()
                        .body("brands",not(empty()))
                        .extract().response();
          //get all brands
        List<String> brands=response.jsonPath().getList("brands.brand");

        //check num of all unique brands =8
        long uniqueCount=brands.stream().distinct().count();
        Assert.assertEquals(uniqueCount,8,"Unique brands should be = 8");

        String targetBrand = "Polo";
        Assert.assertTrue(brands.contains(targetBrand), "Target brand not found: " + targetBrand);
//save brand keyword for TC_04
        String brandKeyword = targetBrand;
        System.out.println("TC03 — brand_keyword saved: " + brandKeyword);
    }

    @Test(priority = 4)
    @Description("Search products using brand keyword (Polo) and verify results returned")
    public void searchProductUsingBrandKeyword_TC04(){

        String brandKeyword="Polo";
        Response response=
        given()
                .baseUri(baseUrl)
                .formParam("search_product",brandKeyword)
         .when()
                .post("/api/searchProduct")
         .then()
                .statusCode(200)
                .body("products",not(empty()))
                .extract().response();

        assert response.asString().contains("\"responseCode\": 200");
        System.out.println("TC04_Response: " + response.asString());
        System.out.println("__________________________________________________________________________________________");


    }

    }


