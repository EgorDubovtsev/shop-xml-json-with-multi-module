package org.interfaces.api;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public interface JsonConverter<T> {
    void convertToJson(File xmlFile, File jsonFile, Class<T> classForConvert) throws IOException;

    void convertToXml(File jsonFile, File fileForConvert, Class<T> classForConvert, GeneratorXml generatorXml) throws IOException, JAXBException;
}
