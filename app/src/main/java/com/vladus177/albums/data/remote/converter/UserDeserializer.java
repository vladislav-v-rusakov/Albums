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

    private static final String na = "N/A";

    @Override
    public UserEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : 0;
        final String name = jsonObject.has("name") ? jsonObject.get("name").getAsString() : na;
        final String username = jsonObject.has("username") ? jsonObject.get("username").getAsString() : na;
        final String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : na;
        final String phone = jsonObject.has("phone") ? jsonObject.get("phone").getAsString() : na;
        final String website = jsonObject.has("website") ? jsonObject.get("website").getAsString() : na;

        JsonElement address = json.getAsJsonObject().get("address");
        AddressEntry addressEntry;
        if (address != null) {
            addressEntry = deserializeUserAddress(address.getAsJsonObject());
        } else {
            addressEntry = new AddressEntry(na, na, na, na, new LocationEntry(na, na));
        }

        JsonElement company = json.getAsJsonObject().get("company");
        CompanyEntry companyEntry;
        if (company != null) {
            companyEntry = deserializeUserCompany(company.getAsJsonObject());
        } else {
            companyEntry = new CompanyEntry(na, na, na);
        }

        return new UserEntry(id, name, username, email, addressEntry, phone, website, companyEntry);
    }

    private AddressEntry deserializeUserAddress(JsonObject jsonAddress) {
        final String street = jsonAddress.has("street") ? jsonAddress.get("street").getAsString() : na;
        final String suite = jsonAddress.has("suite") ? jsonAddress.get("suite").getAsString() : na;
        final String city = jsonAddress.has("city") ? jsonAddress.get("city").getAsString() : na;
        final String zipCode = jsonAddress.has("zipcode") ? jsonAddress.get("zipcode").getAsString() : na;

        JsonElement location = jsonAddress.getAsJsonObject().get("geo");
        LocationEntry locationEntry;
        if (location != null) {
            locationEntry = deserializeUserLocation(location.getAsJsonObject());
        } else {
            locationEntry = new LocationEntry(na, na);
        }

        return new AddressEntry(street, suite, city, zipCode, locationEntry);
    }

    private LocationEntry deserializeUserLocation(JsonObject jsonLocation) {
        final String lat = jsonLocation.has("lat") ? jsonLocation.get("lat").getAsString() : na;
        final String lng = jsonLocation.has("lng") ? jsonLocation.get("lng").getAsString() : na;

        return new LocationEntry(lat, lng);
    }

    private CompanyEntry deserializeUserCompany(JsonObject jsonCompany) {
        final String name = jsonCompany.has("name") ? jsonCompany.get("name").getAsString() : na;
        final String catchPhrase = jsonCompany.has("catchPhrase") ? jsonCompany.get("catchPhrase").getAsString() : na;
        final String bs = jsonCompany.has("bs") ? jsonCompany.get("bs").getAsString() : na;

        return new CompanyEntry(name, catchPhrase, bs);
    }
}
