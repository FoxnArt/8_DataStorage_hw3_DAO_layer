select product_name
from ORDERS O
         INNER JOIN CUSTOMERS C
                    ON O.customer_id = C.id
where name = :name;