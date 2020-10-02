1. Create a database named "sm" if it is not already exists
2. Then create "post" schema if it is not already exists 

3.  Run following DDL to create "place" table

    CREATE TABLE post.place (

        id uuid NOT NULL,
        name varchar(256) NOT NULL,
        CONSTRAINT place_pkey PRIMARY KEY (id)
    );
4. To insert seed data in place table run following command

    INSERT INTO post.place (id,"name") VALUES 
    ('8f52565f-9adc-48d5-871e-3839790f27b9','Bandarban')
    ,('480ee890-8c8e-45f9-b7c8-02e7bbeb320d','Sylhet')
    ,('2c5159b2-c58d-423b-bff8-31ccc3c1be05','Khulna')
    ;

5.  Run following DDL to create "post" table

    CREATE TABLE post.post (
    
        id uuid NOT NULL,
        user_id uuid NOT NULL,
        description varchar(512) NOT NULL,
        visibility varchar(16) NOT NULL,
        place_id uuid NOT NULL,
        CONSTRAINT post_pkey PRIMARY KEY (id)
    );
    
    ALTER TABLE post.post ADD CONSTRAINT fk_place_id_post FOREIGN KEY (place_id) REFERENCES post.place(id);

6. Run mvn clean spring-boot:run

7. To get swagger documentation visit following link
http://localhost:8082/swagger-ui.html

8. ER diagram of post schema

![alt text](https://github.com/shaikhhafiz/sm-post-service/blob/master/ERD.png?raw=true)