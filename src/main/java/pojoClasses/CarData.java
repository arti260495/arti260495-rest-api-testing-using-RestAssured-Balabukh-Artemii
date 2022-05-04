package pojoClasses;

public class CarData {
    private Integer id;
    private String engineType;
    private String mark;
    private String model;
    private Double price;

    public CarData() {}


    public CarData(Integer id, String engineType, String mark, String model, Double price) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }
}
