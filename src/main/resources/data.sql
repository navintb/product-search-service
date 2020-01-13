insert into brand (id, name, status, create_date, update_date) values (1, 'Brand-1', 'ACTIVE', curdate(), curdate());
insert into brand (id, name, status, create_date, update_date) values (2, 'Brand-2', 'ACTIVE', curdate(), curdate());

insert into category (id, name, status, create_date, update_date) values (1, 'Shirt', 'ACTIVE', curdate(), curdate());
insert into category (id, name, status, create_date, update_date) values (2, 'Jeans', 'ACTIVE', curdate(), curdate());

insert into color (id, name, status, create_date, update_date) values (1, 'Blue', 'ACTIVE', curdate(), curdate());
insert into color (id, name, status, create_date, update_date) values (2, 'Red', 'ACTIVE', curdate(), curdate());

insert into size (id, name, category_id, status, create_date, update_date) values (1, 'L', 1, 'ACTIVE', curdate(), curdate());
insert into size (id, name, category_id, status, create_date, update_date) values (2, 'XL', 1, 'ACTIVE', curdate(), curdate());
insert into size (id, name, category_id, status, create_date, update_date) values (3, 'L', 2, 'ACTIVE', curdate(), curdate());
insert into size (id, name, category_id, status, create_date, update_date) values (4, 'XL', 2, 'ACTIVE', curdate(), curdate());

insert into product (id, name, brand_id, category_id, status, create_date, update_date) values (1, 'Product-1',1,1, 'ACTIVE', curdate(), curdate());
insert into product (id, name, brand_id, category_id, status, create_date, update_date) values (2, 'Product-2',2,2, 'ACTIVE', curdate(), curdate());


insert into product_item (id, sku, product_id, color_id, size_id, price, count, status, create_date, update_date) values (1, 'SH0000001',1,1,1,1000,10, 'ACTIVE', curdate(), curdate());
insert into product_item (id, sku, product_id, color_id, size_id, price, count, status, create_date, update_date) values (2, 'SH0000002',1,1,2,800,8, 'ACTIVE', curdate(), curdate());
insert into product_item (id, sku, product_id, color_id, size_id, price, count, status, create_date, update_date) values (3, 'SH0000003',2,2,3,1200,10, 'ACTIVE', curdate(), curdate());
insert into product_item (id, sku, product_id, color_id, size_id, price, count, status, create_date, update_date) values (4, 'SH0000004',2,2,4,900,10, 'ACTIVE', curdate(), curdate());