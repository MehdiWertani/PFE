create
definer = root@`%` procedure launch_campagne(IN campagneId bigint, IN campagneName varchar(255))
BEGIN

    DECLARE count int;
    set count = (select count(*) from result_collection); SELECT * FROM campagne_marketings WHERE id = campagneId AND name like campagneName;

    set count = count + 1;
    insert into result_collection (id, error_description, execution_status, id_campagne, ko_sms_nbr, succeeded_sms_nbr)
    VALUES (count, null, 'OK', campagneId, 10, 25);
END;

