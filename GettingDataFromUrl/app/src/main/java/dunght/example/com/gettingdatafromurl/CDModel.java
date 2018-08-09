package dunght.example.com.gettingdatafromurl;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class CDModel {
    @Element(name = "TITLE")
    String title;

    @Element(name = "ARTIST")
    String artist;

    @Element(name = "COMPANY")
    String company;

    @Element(name = "YEAR")
    int year;

    @Element(name = "PRICE")
    float price;

    @Element(name = "COUNTRY")
    String country;
}
