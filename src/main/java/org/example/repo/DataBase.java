package org.example.repo;

import org.example.entity.DoctorEntity;
import org.example.entity.PriceEntity;
import org.example.entity.RequestEntity;
import org.example.entity.UserEntity;
import org.example.enums.GenderEnum;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<UserEntity> userList = new ArrayList<>();
    private ArrayList<RequestEntity> requestList = new ArrayList<>();

    private ArrayList<PriceEntity> priceList = new ArrayList<>();
    private ArrayList<DoctorEntity> doctorList = new ArrayList<>();

    public boolean saveUser( UserEntity userEntity ) {

        if ( userEntity.getId() != null && userEntity.getPhoneNumber() != null ) {
            userList.add(userEntity);
            return true;
        }

        return false;
    }

    public boolean saveRequest( RequestEntity entity ) {
        if ( entity.getUserId() != null && entity.getPhoneNumber() != null ) {
            requestList.add(entity);
            return true;
        }

        return false;
    }

    public UserEntity getUserById( Long id ) {
        for ( UserEntity entity: userList ) {
            if ( entity.getId().equals( id ) ) {
                return entity;
            }
        }

        return null;
    }

    public RequestEntity getRequestByUserId( Long id ) {
        for ( RequestEntity entity: requestList ) {
            if ( entity.getUserId().equals( id ) ) {
                return entity;
            }
        }

        return null;
    }

    public DoctorEntity getDoctorByIndex(int index){
        return doctorList.get(index);
    }

    public ArrayList<UserEntity> getUSerList(){
        return userList;
    }

    public ArrayList<RequestEntity> getRequestList() {
        return requestList;
    }

    public ArrayList<PriceEntity> getPriceList(){
        return priceList;
    }

    public void savePrice( PriceEntity entity ) {
        if ( entity.getPrice() != 0 && entity.getId() != null && entity.getServiceName() != null ){
            priceList.add(entity);
        }
    }

    public void saveDr( DoctorEntity entity ) {
        doctorList.add(entity);
    }

    public ArrayList<DoctorEntity> getDrList(){
        return doctorList;
    }

    public void createDr(){
        DoctorEntity entity1 = new DoctorEntity(1L, "Vish Vxarat", "25 yillik tajribaga ega", 50, GenderEnum.MALE);
        DoctorEntity entity2 = new DoctorEntity(2L, "Ali Aliyev", "30 yillik tajribaga ega", 50, GenderEnum.MALE);
        DoctorEntity entity3 = new DoctorEntity(3L, "Luka Modric", "10 yillik tajribaga ega", 40, GenderEnum.MALE);
        saveDr( entity1 );
        saveDr( entity2 );
        saveDr( entity3 );
    }

    public void createPrice(){
        PriceEntity entity = new PriceEntity(1L, 9999900, "Breket", "elastik");
        PriceEntity entity2 = new PriceEntity(2L, 5000000, "Tish qo'yish", "oltin tish");
        PriceEntity entity3 = new PriceEntity(3L, 999900, "Tish olish", "ipga boylab sug'irish");
        PriceEntity entity4 = new PriceEntity(4L, 2999900, "Chistka", "karcher bilan yuvish");
        PriceEntity entity5 = new PriceEntity(5L, 19990000, "Implantatsiya", "Drel bilan tish qotirish" );
        PriceEntity entity6 = new PriceEntity(6L, 29990000, "Maslahatlashish", "Tish psixologi" );

        savePrice( entity );
        savePrice( entity2 );
        savePrice( entity3 );
        savePrice( entity4 );
        savePrice( entity5 );
        savePrice( entity6 );
    }


}
