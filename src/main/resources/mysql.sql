SELECT id FROM tbl_che168
WHERE  car_url  NOT  IN  ( SELECT source_url FROM tbl_che168_car);

SELECT * FROM A
WHERE
    NOT  EXISTS  (
        SELECT 1
        FROM B
        WHERE B.id = A.id );

SELECT
  A.*
FROM
  A  LEFT JOIN B
    ON (A.id = B.id)
WHERE
  b.id  IS  NULL