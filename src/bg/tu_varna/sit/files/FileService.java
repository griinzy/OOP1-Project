package bg.tu_varna.sit.files;

import bg.tu_varna.sit.enums.Unit;
import bg.tu_varna.sit.model.Location;
import bg.tu_varna.sit.model.Manufacturer;
import bg.tu_varna.sit.model.Product;
import bg.tu_varna.sit.model.Warehouse;
import bg.tu_varna.sit.util.DateParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static FileService instance;
    private String filePath;
    private final Warehouse warehouse = Warehouse.getInstance();

    private FileService() {}

    public static FileService getInstance() {
        if(instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isFileOpen() {
        return filePath != null;
    }

    public void save() throws Exception {
        saveToPath(filePath);
    }

    public void saveAs(String path) throws Exception {
        saveToPath(path);
    }
    private void saveToPath(String path) throws Exception {
        List<Product> products = warehouse.getProducts();

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("warehouse");
        doc.appendChild(root);

        for(Product p : products) {
            Element product = doc.createElement("product");
            product.appendChild(addProperty(doc, "name", p.getName()));
            product.appendChild(addProperty(doc, "expiry", p.getExpiryDate().format(DateParser.formatter)));
            product.appendChild(addProperty(doc, "added", p.getDateAdded().format(DateParser.formatter)));
            product.appendChild(addProperty(doc, "manufacturer", p.getManufacturer().getName()));
            product.appendChild(addProperty(doc, "unit", p.getUnit().name()));
            product.appendChild(addProperty(doc, "quantity", String.valueOf(p.getQuantity())));
            product.appendChild(addProperty(doc, "location", p.getLocation().toString()));
            product.appendChild(addProperty(doc, "comment", p.getComment()));
            root.appendChild(product);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(new File(path)));
    }

    private Element addProperty(Document doc, String tag, String value) {
        Element elem = doc.createElement(tag);
        elem.setTextContent(value);
        return elem;
    }

    public List<Product> load() throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(filePath));

        List<Product> products = new ArrayList<>();
        NodeList nodes = doc.getElementsByTagName("product");

        for(int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element) nodes.item(i);
            String name = elem.getElementsByTagName("name").item(0).getTextContent();
            LocalDate expiry = DateParser.parse(elem.getElementsByTagName("expiry").item(0).getTextContent());
            LocalDate added = DateParser.parse(elem.getElementsByTagName("added").item(0).getTextContent());
            Manufacturer manufacturer = warehouse.getManufacturer(elem.getElementsByTagName("manufacturer").item(0).getTextContent());
            Unit unit = Unit.parse(elem.getElementsByTagName("unit").item(0).getTextContent());
            double quantity = Double.parseDouble(elem.getElementsByTagName("quantity").item(0).getTextContent());
            Location location = Location.parse(elem.getElementsByTagName("location").item(0).getTextContent());
            String comment  = elem.getElementsByTagName("comment").item(0).getTextContent();

            Product product = new Product(name, expiry, added, manufacturer, unit, quantity, location.getSection(), comment);
            product.setLocation(location);
            products.add(product);
        }
        return products;
    }
}
