package api;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pojoClasses.CarData;
import pojoClasses.HouseData;
import pojoClasses.UserData;
import utility.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests extends BaseTest{

    @Test
    public void checkUsersInfo(){
        setBaseURI();

        // получение списка пользователей
        List<UserData> users = given()
                .spec(requestSpec)
                .when()
                .get("/users")
                .then().log().all()
                .extract().body().jsonPath().getList("", UserData.class);

        // проверка конкретных значений
        Assert.assertEquals(users.get(0).getFirstName(), "Vasiliy");
        Assert.assertEquals(users.get(0).getSecondName(), "Rubenstein");
        Assert.assertEquals(users.get(0).getSex(), "MALE");
        Assert.assertEquals((users.get(0).getMoney()), (Double)930000.0);
        Assert.assertEquals(users.get(0).getId().toString(), "1");
        Assert.assertEquals(users.get(0).getAge().toString(), "42");
    }

    @Test
    public void checkUserInfoById(){
        setBaseURI();

        //получение пользователя по id, проверка значений
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/user/1")
                .then().log().all()
                .body("firstName", equalTo("Vasiliy"))
                .body("secondName", notNullValue())
                .body("age", greaterThan(30))
                .body("sex", not("FEMALE"))
                .extract().response();
    }

    @Test
    public void checkCarsInfo(){
        setBaseURI();

        // получение списка автомобилей
        List<CarData> cars = given()
                .spec(requestSpec)
                .when()
                .get("/cars")
                .then().log().all()
                .extract().body().jsonPath().getList("", CarData.class);

        // проверка конкретных значений
        Assert.assertEquals(cars.get(0).getEngineType(), "Electric");
        Assert.assertEquals(cars.get(0).getMark(), "Tesla");
        Assert.assertEquals(cars.get(0).getModel(), "Model X");
        Assert.assertEquals(cars.get(0).getPrice(), (Double) 70000.00);
        Assert.assertEquals(cars.get(0).getId(), (Integer) 1);


    }

    @Test
    public void AddCar(){
        setBaseURI();

        //создание автомобиля
        Map<String,String> car = new HashMap<>();
        car.put("engineType", "Gasoline");
        car.put("mark", "volkswagen");
        car.put("model", "polo");
        car.put("price", "black");

        given()
                .body(car)
                .when().post("/addCar").then()
                .statusCode(201);
    }

    @Test
    public void AddUser(){
        setBaseURI();

        //создание пользователя
        Map<String,String> user = new HashMap<>();
        user.put("engineType", "Artemii");
        user.put("secondName", "Balabukh");
        user.put("age", "27");
        user.put("sex", "MALE");
        user.put("money", "9999999.00");

        given()
                .body(user)
                .when().post("/addUser").then()
                .statusCode(201);
    }

    @Test
    public void AddHouse(){
        setBaseURI();

        //создание парковок
        JSONObject parkingPlace1 = new JSONObject();
        parkingPlace1.put("isWarm", true);
        parkingPlace1.put("isCovered", true);
        parkingPlace1.put("placesCount", 1);

        JSONObject parkingPlace2 = new JSONObject();
        parkingPlace2.put("isWarm", false);
        parkingPlace2.put("isCovered", false);
        parkingPlace2.put("placesCount", 3);

        //массив парковок
        JSONArray parkingPlaces = new JSONArray();
        parkingPlaces.add(parkingPlace1);
        parkingPlaces.add(parkingPlace2);

        //создание дома
        JSONObject house = new JSONObject();
        house.put("floorCount", "10");
        house.put("price", "12321");
        house.put("parkingPlaces", parkingPlaces);

        given()
                .body(house)
                .when().post("/addHouse").then()
                .statusCode(201);
    }

    @Test
    public void BuyCar(){
        setBaseURI();
        given()
                .param("userId", 1)
                .param("carId", 1)
                .when().post("/user/{userId}/buyCar/{carId}").then()
                .statusCode(201);

    }

    @Test
    public void Settle(){
        setBaseURI();
        given()
                .param("houseId", 1)
                .param("userId", 1)
                .when().post("/house/{houseId}/settle/{userId}").then()
                .statusCode(201);
    }

    @Test
    public void Money(){
        setBaseURI();
        given()
                .param("userId", 1)
                .param("amount", 898989.00)
                .when().post("/user/{userId}/money/{amount}").then()
                .statusCode(201);
    }



}
