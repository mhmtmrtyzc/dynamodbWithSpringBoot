package io.springlab.dynamodbtuto.entitys.customer;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import io.springlab.dynamodbtuto.converters.Converters.LocalDateTimeConverter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "customer")
public class Customer {
    @DynamoDBHashKey(attributeName = "customerId")
    @DynamoDBAutoGeneratedKey
    private String customerId;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String surname;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime created;
    @DynamoDBAttribute
    private Address address;

    
    public void setCreated(LocalDateTime created) {
        
        this.created = Objects.isNull(created) ? LocalDateTime.now() : created;
    }


    public Customer(CustomerBuilder customerBuilder) {
        this.customerId = customerBuilder.customerId;
        this.name = customerBuilder.name;
        this.surname = customerBuilder.surname;
        this.email = customerBuilder.email;
        this.created = LocalDateTime.now();
        this.address = customerBuilder.address;
    }


    public static class CustomerBuilder {
        private String customerId;
        private String name;
        private String surname;
        private String email;
        private Address address;
        
        public CustomerBuilder(){

        }

        public CustomerBuilder setCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder setName(String name){
            this.name = name;
            return this;
        }

        public CustomerBuilder setSurname(String surname){
            this.surname = surname;
            return this;
        }

        public CustomerBuilder setEmail(String email){
            this.email = email;
            return this;
        }

        public CustomerBuilder setCustomerId(Address address){
            this.address = address;
            return this;
        }

        public Customer build(){

            return new Customer(this);
        }

    }
    
}
