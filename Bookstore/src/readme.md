##1. ��Ŀ׼������

### 1.2���������jar��

		* c3p0
		* dbutils
		* beanutils
		* mysql-connection
		* dom4j
		* log4j
		* jstl
		
###1.3����

		* domain
		* dao
		* service
		* controller
		* utils
		* filter
		* factory
		
### 1.4һЩȫ�ֵĹ������Լ�������
		* webutils
		* jdbcutils
		* encodefilter
		* �ȵ�
##2.ʵ����ƺͱ����
	
###2.1. ʵ�����
	
	Category
		private String id;
		private String name;
		private String description;
	
	
	Book 
		private String id;
		private String name;
		private double price;
		private String author;
		private String image; 
		private String description;
		private Category category;
	Order
		private String id;
		private Date ordertime; 
		private boolean state;   
		private double price;  
		
		private User user;    
		private Set orderitems;  
	OrderItem
		private String id;
		private int quantity;
		private double price;
		private Book book;   //��ס�������������ı���
	User
		private String id;
		private String username;
		private String password;
		private String phone;
		private String cellphone;
		private String email;
		private String address;
		
		
###2.2 �����

		create database bookstore;
		use bookstore;
		
		create table(
	 		id varchar(40) primary key,
	 		name varchar(40) not null unique,
	 		description varchar(255)
	 	);
		create table user(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
		phone varchar(20) not null,
		cellphone varchar(20) not null,
		email varchar(40) not null,
		address varchar(255) not null
		);
		
		create table order(
			id varchar(40) primary key,
			ordertime time not null,
			state boolean nol null,
			price decimal(8,2) not null,
			user_id varchar(40),
			constraint user_id_FK foreign key reference user(id)
		);
		
		create table book(
			id varchar(40) primary key,
			name varchar(40) not null,
			price decimal(8,2) not null,
			description varchar(255),
			image blob not null,
			author varchar(40) not null,
			category_id varchar(40),
			constraint category_id_FK foreign key(category_id) reference catagory(id)
		);
		
		create table orderitem(
				id varchar(40) primary key,
				quantity int(20) not null,
				price decimal(8,2) not null,
				book_id varchar(40),
				constraint book_id_FK foreign key(book_id) reference book(id),
				order_id varchar(40),
				constraint order_id_FK foreign key(order_id) reference order(id)
		);

		