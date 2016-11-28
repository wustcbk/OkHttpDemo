package com.example.ede67167.DownLoadView;



/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String imgSrc;
     private String title;
     private String desribe;


    // Constructors

    /** default constructor */
    public Product() {
    }

    
    /** full constructor */
    public Product(String imgSrc, String title, String desribe) {
        this.imgSrc = imgSrc;
        this.title = title;
        this.desribe = desribe;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }
    
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesribe() {
        return this.desribe;
    }
    
    public void setDesribe(String desribe) {
        this.desribe = desribe;
    }
   








}