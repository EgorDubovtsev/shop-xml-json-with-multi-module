package impl.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import impl.service.SimpleGeneratorXml;
import impl.service.SimpleJsonConverter;
import impl.service.SimpleReaderXml;
import impl.service.XmlValidator;
import org.interfaces.api.GeneratorXml;
import org.interfaces.api.JsonConverter;
import org.interfaces.api.ReaderXml;
import org.pojos.api.category.Category;
import org.pojos.api.category.SimpleCategory;
import org.pojos.api.entity.Product;
import org.pojos.api.entity.SimpleProduct;
import org.pojos.api.shop.Shop;
import org.pojos.api.shop.SimpleShop;
import org.pojos.api.subcategory.SimpleSubcategory;
import org.pojos.api.subcategory.Subcategory;
import org.pojos.api.utils.Color;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws JAXBException {

        Product car1 = new SimpleProduct();
        car1.setMaker("BMW");
        car1.setModel("aa111");
        car1.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        car1.setPrice(2000.5);
        car1.setInStock(true);
        car1.setColor(Color.BLACK);
        car1.setTest("Attribute value");

        Product car2 = new SimpleProduct();
        car2.setMaker("MERCEDES");
        car2.setModel("ab212");
        car2.setCreationDate(LocalDate.of(2002, Month.JANUARY, 14));
        car2.setPrice(4000);
        car2.setInStock(false);
        car2.setColor(Color.RED);
        car2.setTest("Attribute value4");


        Product airplane1 = new SimpleProduct();
        airplane1.setMaker("GOVERNMENT");
        airplane1.setModel("tt171");
        airplane1.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        airplane1.setPrice(2000.5);
        airplane1.setInStock(true);
        airplane1.setColor(Color.BLACK);
        airplane1.setTest("Attribute value3");


        Product airplane2 = new SimpleProduct();
        airplane2.setMaker("YOUNG COMPANY");
        airplane2.setModel("jf555");
        airplane2.setCreationDate(LocalDate.of(2006, Month.APRIL, 21));
        airplane2.setPrice(2000.5);
        airplane2.setInStock(false);
        airplane2.setColor(Color.BLACK);
        airplane2.setTest("Attribute value2");


        Subcategory civilSubcategory = new SimpleSubcategory("civil airplane");
        civilSubcategory.addProduct(airplane2);

        Subcategory militarySubcategory = new SimpleSubcategory("military airplane");
        militarySubcategory.addProduct(airplane1);

        Subcategory racingSubcategory = new SimpleSubcategory("racing car");
        racingSubcategory.addProduct(car1);

        Subcategory truckSubcategory = new SimpleSubcategory("truck car");
        truckSubcategory.addProduct(car2);

        Category airplaneCategory = new SimpleCategory("airplane");
        airplaneCategory.addSubcategory(militarySubcategory);
        airplaneCategory.addSubcategory(civilSubcategory);

        Category carCategory = new SimpleCategory("car");
        carCategory.addSubcategory(racingSubcategory);
        carCategory.addSubcategory(truckSubcategory);

        Shop shop = new SimpleShop();
        shop.addCategory(airplaneCategory);
        shop.addCategory(carCategory);

        File xmlFile = new File("shop.xml");

        GeneratorXml generatorXml = new SimpleGeneratorXml();
        generatorXml.generateXmlJAXB(shop, xmlFile);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        File xmlForReading = new File(resourceBundle.getString("pathToShop"));

        ReaderXml<SimpleShop> readerXml = new SimpleReaderXml<>();
        System.out.println(readerXml.readXml(xmlForReading, SimpleShop.class));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonConverter<SimpleShop> simpleShopJsonConverter = new SimpleJsonConverter<>(objectMapper, readerXml);
        File jsonFile = new File("shop.json");
        try {
            simpleShopJsonConverter.convertToJson(xmlFile, jsonFile, SimpleShop.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File convertedShop = new File("convertedShop.xml");
        try {
            simpleShopJsonConverter.convertToXml(jsonFile, convertedShop, SimpleShop.class, generatorXml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
