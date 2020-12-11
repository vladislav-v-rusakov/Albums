package com.vladus177.albums.data.remote.converter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vladus177.albums.data.remote.model.AddressEntry;
import com.vladus177.albums.data.remote.model.CompanyEntry;
import com.vladus177.albums.data.remote.model.LocationEntry;
import com.vladus177.albums.data.remote.model.UserEntry;

import java.lang.reflect.Type;


public class UserDeserializer implements JsonDeserializer<UserEntry> {
    @Override
    public UserEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final Long id = jsonObject.get("id").getAsLong();
        final String name = jsonObject.get("name").getAsString();
        final String username = jsonObject.get("username").getAsString();
        final String email = jsonObject.get("email").getAsString();
        final String phone = jsonObject.get("phone").getAsString();
        final String website = jsonObject.get("website").getAsString();


        JsonElement address = json.getAsJsonObject().get("address");
        JsonElement company = json.getAsJsonObject().get("company");
        AddressEntry addressEntry = deserializeUserAddress(address.getAsJsonObject());
        CompanyEntry companyEntry = deserializeUserCompany(company.getAsJsonObject());

        return new UserEntry(
                id != null ? id : 0L,
                isNotNullOrEmpty(name) ? name : "",
                isNotNullOrEmpty(username) ? username : "",
                isNotNullOrEmpty(email) ? email : "",
                addressEntry,
                isNotNullOrEmpty(phone) ? phone : "",
                isNotNullOrEmpty(website) ? website : "",
                companyEntry);
    }

    private AddressEntry deserializeUserAddress(JsonObject jsonAddress) {
        final String street = jsonAddress.get("street").getAsString();
        final String suite = jsonAddress.get("suite").getAsString();
        final String city = jsonAddress.get("city").getAsString();
        final String zipCode = jsonAddress.get("zipcode").getAsString();

        JsonElement location = jsonAddress.getAsJsonObject().get("geo");
        LocationEntry locationEntry = deserializeUserLocation(location.getAsJsonObject());

        return new AddressEntry(
                isNotNullOrEmpty(street) ? street : "",
                isNotNullOrEmpty(suite) ? suite : "",
                isNotNullOrEmpty(city) ? city : "",
                isNotNullOrEmpty(zipCode) ? zipCode : "",
                locationEntry);
    }

    private LocationEntry deserializeUserLocation(JsonObject jsonLocation) {
        final String lat = jsonLocation.get("lat").getAsString();
        final String lng = jsonLocation.get("lng").getAsString();

        return new LocationEntry(
                isNotNullOrEmpty(lat) ? lat : "",
                isNotNullOrEmpty(lng) ? lng : "");
    }

    private CompanyEntry deserializeUserCompany(JsonObject jsonCompany) {
        final String name = jsonCompany.get("name").getAsString();
        final String catchPhrase = jsonCompany.get("catchPhrase").getAsString();
        final String bs = jsonCompany.get("bs").getAsString();

        return new CompanyEntry(
                isNotNullOrEmpty(name) ? name : "",
                isNotNullOrEmpty(catchPhrase) ? catchPhrase : "",
                isNotNullOrEmpty(bs) ? bs : "");
    }

    public static boolean isNotNullOrEmpty(String str) {
        return str != null || !str.isEmpty();
    }
}
