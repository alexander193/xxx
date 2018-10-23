package sut.sa.g20.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sut.sa.g20.entity.*;
import sut.sa.g20.repository.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController @CrossOrigin(origins = "http://localhost:4200")
public class G20Controller {
    //Variable for room count
    private int count = 0;
    private int status = 0;
    //==========FOR PROMOTION===========
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionTypeRepository promotionTypeRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private RoomStatusRepository roomStatusRepository;

    //===========FOR CUSTOMER===========
    @Autowired
    private CountryCodeRepository countryCodeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SexRepository sexRepository;
    @Autowired
    private TitleNameRepository titleNameRepository;

    //===========FOR HOTEL===========
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private MemberHotelRepository memberHotelRepository;
    @Autowired
    private RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository;

    //===========FOR RESERVATION===========
    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    public G20Controller(HotelRepository hotelRepository, RoomRepository roomRepository, PromotionRepository promotionRepository,
                         PromotionTypeRepository promotionTypeRepository, RoomTypeRepository roomTypeRepository,
                         ProvinceRepository provinceRepository, RoomStatusRepository roomStatusRepository,
                         CountryCodeRepository countryCodeRepository, CustomerRepository customerRepository,
                         SexRepository sexRepository, TitleNameRepository titleNameRepository,
                         FurnitureRepository furnitureRepository, MemberHotelRepository memberHotelRepository,
                         RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository,
                         ReservationsRepository reservationsRepository){
        //========FOR PROMOTION========
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.promotionRepository = promotionRepository;
        this.promotionTypeRepository = promotionTypeRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.provinceRepository = provinceRepository;
        this.roomStatusRepository = roomStatusRepository;
        //========FOR CUSTOMER========
        this.countryCodeRepository = countryCodeRepository;
        this.customerRepository = customerRepository;
        this.sexRepository = sexRepository;
        this.titleNameRepository = titleNameRepository;
        //========FOR HOTEL========
        this.furnitureRepository = furnitureRepository;
        this.memberHotelRepository = memberHotelRepository;
        this.roomTypeFurnitureManyToManyRepository = roomTypeFurnitureManyToManyRepository;
        //========FOR RESERVATION========
        this.reservationsRepository = reservationsRepository;
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<HotelEntity> Hotel(){
        return hotelRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/hotelroom", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RoomEntity> HotelRoom(){
        return roomRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/promotiontype", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionTypeEntity> PromotionType(){
        return promotionTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RoomTypeEntity> RoomType(){
        return roomTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/promotion")
    public Collection<PromotionEntity> Promotion(){
        return promotionRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/province", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ProvinceEntity> Province(){
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path ="/roomstatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RoomStatusEntity> RoomStatus(){
        return roomStatusRepository.findAll().stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @GetMapping(path = "/room/getdata/{hotelSelect}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RoomTypeEntity> getRoomType(@PathVariable String hotelSelect) {
        HotelEntity hotel = hotelRepository.findByhotelNameEng(hotelSelect);
        Collection<RoomEntity> R = roomRepository.findByhotel(hotel);
        Collection<RoomTypeEntity> type = new ArrayList<>();
        for(RoomEntity room: R){
            type.add(room.getRoomTypeEntity());
        }
        return type.stream().collect(Collectors.toList());
    }

    //=========================FOR PROMOTION===========================
    @PutMapping("/promotion/update/{hotelName}/{roomType}/{promotionType}/{discount}/{dateStart}/{dateEnd}/{detail}/{id}")
    public PromotionEntity newPromotion(@RequestBody PromotionEntity newPromotionEntity, @PathVariable String hotelName,
                                                  @PathVariable String roomType, @PathVariable String promotionType,
                                                  @PathVariable int discount, @PathVariable Date dateStart,
                                                  @PathVariable Date dateEnd, @PathVariable String detail, @PathVariable Long id) {
        PromotionEntity newPromotion = promotionRepository.getOne(id);

        HotelEntity hotelEntity = hotelRepository.findByhotelNameEng(hotelName);
        RoomTypeEntity room = roomTypeRepository.findByroomType(roomType);
        PromotionTypeEntity promoType = promotionTypeRepository.findByPromotionType(promotionType);
        newPromotion.setHotelEntity(hotelEntity);
        newPromotion.setRoomTypeEntity(room);
        newPromotion.setPromotionTypeEntity(promoType);
        newPromotion.setDiscount(discount);
        newPromotion.setDateStart(dateStart);
        newPromotion.setDateEnd(dateEnd);
        newPromotion.setDetail(detail);

        return promotionRepository.save(newPromotion);
    }

    //=========================FOR PROMOTION===========================
    @DeleteMapping("promotion/{id}")
    public void deletePromotion(@PathVariable Long id){
        promotionRepository.deleteById(id);
    }

    //=========================FOR PROMOTION===========================
    @PostMapping("/promotion/{hotelName}/{roomType}/{promotionType}/{discount}/{dateStart}/{dateEnd}/{detail}")
    public PromotionEntity promotion(@RequestBody PromotionEntity newPromotionEntity, @PathVariable String hotelName,
                                     @PathVariable String roomType, @PathVariable String promotionType,
                                     @PathVariable int discount, @PathVariable Date dateStart,
                                     @PathVariable Date dateEnd, @PathVariable String detail) {

        PromotionEntity p = new PromotionEntity();

        HotelEntity hotelEntity = hotelRepository.findByhotelNameEng(hotelName);
        RoomTypeEntity room = roomTypeRepository.findByroomType(roomType);
        PromotionTypeEntity promoType = promotionTypeRepository.findByPromotionType(promotionType);

        p.setHotelEntity(hotelEntity);
        p.setRoomTypeEntity(room);
        p.setPromotionTypeEntity(promoType);
        p.setDiscount(discount);
        p.setDateStart(dateStart);
        p.setDateEnd(dateEnd);
        p.setDetail(detail);

        return promotionRepository.save(p);
    }

    //==================FOR CUSTOMER===================
    @GetMapping(path = "/countryCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CountryCodeEntity> CountryCode(){
        return countryCodeRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR CUSTOMER===================
    @GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CustomerEntity> Customer(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR CUSTOMER===================
    @GetMapping(path = "/sex", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<SexEntity> Sex(){
        return sexRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR CUSTOMER===================
    @GetMapping(path = "/TitleNameEntity", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<TitleNameEntity> TitleName(){
        return titleNameRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR CUSTOMER===================
    @PostMapping("/customer/{titleName}/{customerName}/{sex}/{email}/{pass}/{countryCode}/{phone}/{address}")
    public CustomerEntity customer(@RequestBody CustomerEntity cus, @PathVariable String titleName, @PathVariable String customerName,
                                   @PathVariable String sex, @PathVariable String email, @PathVariable String pass, @PathVariable String countryCode,
                                   @PathVariable String phone, @PathVariable String address) {
        CustomerEntity customerEntity = new CustomerEntity();
        System.out.println("zzz");
        TitleNameEntity title = titleNameRepository.findBytitlename(titleName);
        CountryCodeEntity countrycode = countryCodeRepository.findBycountrycode(countryCode);
        SexEntity csex = sexRepository.findBycustomersex(sex);

        customerEntity.setTitleName(title);
        customerEntity.setCustomername(customerName);
        customerEntity.setCustomeremail(email);
        customerEntity.setCountry(countrycode);
        customerEntity.setCustomerphone(phone);
        customerEntity.setCustomeraddress(address);
        customerEntity.setCustomersex(csex);
        customerEntity.setCustomerpass(pass);

        return customerRepository.save(customerEntity);
    }

    //==================FOR HOTEL===================
    @GetMapping("/furniture/{name}")
    public FurnitureEntity newFurniture(@PathVariable final String  name){
        FurnitureEntity set = new FurnitureEntity();
        set.setFurnitureName(name);
        return furnitureRepository.save(set);
    }

    //==================FOR HOTEL===================
    @GetMapping("/furnitures")
    public Collection<FurnitureEntity> Furniture() {
        return furnitureRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @GetMapping("/hotel/{hotelNameThai}/{name}/{villageNo}/{houseNo}/{building}/{village}/{alleyLane}/{road}/{subDistrictSubArea}/{districtArea}/{postCode}/{mobilePhone}/{phone}/{fax}/{province}/{memberName}")
    public HotelEntity newHotel(@PathVariable final String  name,@PathVariable String province,@PathVariable int villageNo
            ,@PathVariable String houseNo, @PathVariable String building, @PathVariable String village, @PathVariable String alleyLane,
                                @PathVariable String road, @PathVariable String subDistrictSubArea, @PathVariable String districtArea, @PathVariable int postCode,
                                @PathVariable String mobilePhone, @PathVariable String phone, @PathVariable String fax, @PathVariable String hotelNameThai,@PathVariable String memberName){
        ProvinceEntity p = provinceRepository.findByName(province);
        MemberHotelEntity member = memberHotelRepository.findByName(memberName);
        HotelEntity hotels = new HotelEntity();
        hotels.setHotelNameThai(hotelNameThai);
        hotels.setHotelNameEng(name);
        hotels.setVillageNo(villageNo);
        hotels.setHouseNo(houseNo);
        hotels.setBuilding(building);
        hotels.setVillage(village);
        hotels.setAlleyLane(alleyLane);
        hotels.setRoad(road);
        hotels.setSubDistrictSubArea(subDistrictSubArea);
        hotels.setDistrictArea(districtArea);
        hotels.setPostCode(postCode);
        hotels.setMobilePhone(mobilePhone);
        hotels.setPhone(phone);
        hotels.setFax(fax);
        hotels.setProvinceEntity(p);
        hotels.setMemberHotel(member);
        return hotelRepository.save(hotels);
    }

    //==================FOR HOTEL===================
    @GetMapping("/hotels")
    public Collection<HotelEntity> hotel() {
        return hotelRepository.findAll().stream().collect(Collectors.toList());
    }


    //==================FOR HOTEL===================
    @GetMapping("/memberhotels")
    public Collection<MemberHotelEntity> memberhotel() {
        return memberHotelRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @GetMapping("/memberhotel/{username}/{password}")
    public MemberHotelEntity newMember(@PathVariable String username,@PathVariable Long password){
        MemberHotelEntity member = new MemberHotelEntity();
        member.setMemberHotelName(username);
        member.setMemberHotelPassword(password);
        return memberHotelRepository.save(member);
    }

    //==================FOR HOTEL===================
    @GetMapping("/memberlogin/{username}/{password}")
    public Boolean isLogin(@PathVariable String username,@PathVariable Long password){
        Long m = memberHotelRepository.isLogin(username,password);
        if(m!=null)
            return true;
        return false;
    }

    //==================FOR HOTEL===================
    @GetMapping("/provinces")
    public Collection<ProvinceEntity> provinces() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @GetMapping("/province/{name}")
    public ProvinceEntity newProvince(@PathVariable String name) {
        ProvinceEntity p = new ProvinceEntity();
        p.setProvinceName(name);
        return this.provinceRepository.save(p);
    }

    //==================FOR HOTEL===================
    @PostMapping("/room/{roomType}/{roomstatus}/{number}/{price}/{roomImg}/{memberUserName}")
    public Boolean update(@RequestBody String room,@PathVariable String memberUserName, @PathVariable String roomType, @PathVariable String roomstatus, @PathVariable final int number, @PathVariable int price,@PathVariable String roomImg)throws JsonParseException, IOException {
        final String decoded = URLDecoder.decode(room, "UTF-8");
        room = decoded;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(room);
        JsonNode jsonImg = actualObj.get("imgSelect");

        if(count != 0) {
            Long mem = memberHotelRepository.findId(memberUserName);
            Long hotelIdFromName = hotelRepository.findHotelIdByMemId(mem);
            System.out.println(hotelIdFromName);
            Long hotelIdFromeId = roomRepository.findByHotelId(hotelIdFromName,number);
            System.out.println(hotelIdFromeId);
            if(hotelIdFromeId != null )
                status = 1;
            else
                status = 0;
        }
        if(status == 0) {
            Long mem = memberHotelRepository.findId(memberUserName);
            RoomStatusEntity rst = roomStatusRepository.findByName(roomstatus);
            HotelEntity hotels = hotelRepository.findHotelByMemberId(mem);
            RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
            RoomEntity no1 = new RoomEntity();
            no1.setRoomNumber(number);
            no1.setRoomPrice(price);
            no1.setHotel(hotels);
            no1.setRoomTypeEntity(rt);
            no1.setRoomStatusEntity(rst);
            no1.setRoomImg(jsonImg.textValue());
            roomRepository.save(no1);
            roomRepository.findAll().stream().collect(Collectors.toList());
            count++;
            return true;
        }
        return false;
    }

    //==================FOR HOTEL===================
    @GetMapping("/rooms")
    public Collection<RoomEntity> Rooms() {
        return roomRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @PutMapping("/updateroomstatus/{roomId}/{hotel}/{roomType}/{roomstatus}/{number}/{price}")
    public RoomEntity editRoom(@RequestBody RoomEntity room,@PathVariable Long roomId,@PathVariable String hotel, @PathVariable String roomType, @PathVariable String roomstatus, @PathVariable final int number, @PathVariable int price){
        HotelEntity ho = hotelRepository.findByName(hotel);
        RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
        RoomStatusEntity rst = roomStatusRepository.findByName(roomstatus);
        return roomRepository.findById(roomId).map(roomedit ->{
                    roomedit.setRoomNumber(number);
                    roomedit.setRoomPrice(price);
                    roomedit.setRoomStatusEntity(rst);
                    roomedit.setHotel(ho);
                    roomedit.setRoomTypeEntity(rt);
                    return roomRepository.save(roomedit);
                }
        ).orElseGet(() ->{
            return roomRepository.save(room);
        });
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomstatuses")
    public Collection<RoomStatusEntity> roomStatus() {
        return roomStatusRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomstatus/{name}")
    public RoomStatusEntity newRoomStatus(@PathVariable String name) {
        RoomStatusEntity rst = new RoomStatusEntity();
        rst.setRoomStatus(name);
        return this.roomStatusRepository.save(rst);
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomtype/{rt}/{bedType}/{numberOfBed}/{maxPeople}")
    public RoomTypeEntity newRoomType(@PathVariable final String  rt,@PathVariable String bedType,@PathVariable int numberOfBed,@PathVariable int maxPeople){
        RoomTypeEntity roomType = new RoomTypeEntity();
        roomType.setRoomType(rt);
        roomType.setBedType(bedType);
        roomType.setNumberOfBed(numberOfBed);
        roomType.setMaxPeople(maxPeople);
        return roomTypeRepository.save(roomType);
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomtypes")
    public Collection<RoomTypeEntity> RoomTypes() {
        return roomTypeRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomtypefurniture/{roomtype}/{furniture}")
    public RoomTypeFurnitureManyToManyEntity newRoomType(@PathVariable final String  roomtype, @PathVariable final String furniture){
        FurnitureEntity fr = furnitureRepository.findByName(furniture);
        RoomTypeEntity rt = roomTypeRepository.findByName(roomtype);
        RoomTypeFurnitureManyToManyEntity rtf = new RoomTypeFurnitureManyToManyEntity();
        rtf.setFurnitureFurnitureRoomTypeManyMany(fr);
        rtf.setRoomtypeFurnitureMany(rt);
        return roomTypeFurnitureManyToManyRepository.save(rtf);
    }

    //==================FOR HOTEL===================
    @GetMapping("/roomtypefurnitures")
    public Collection<RoomTypeFurnitureManyToManyEntity> roomtype_furniture() {
        return roomTypeFurnitureManyToManyRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR RESERVATION===================
    @GetMapping("/Reservations")
    public Collection<ReservationsEntity> Reservations(){
        return  reservationsRepository.findAll().stream().collect(Collectors.toList());
    }

    //==================FOR RESERVATION===================
    @GetMapping("/Reservations/{hotel}/{roomtype}/{detail}/{numberofpeople}/{numberofroom}/{dateStart}/{dateEnd}")
    public ReservationsEntity newReser(@PathVariable String hotel,@PathVariable String roomtype,@PathVariable String detail,@PathVariable int numberofpeople,@PathVariable int numberofroom
            ,@PathVariable String dateStart,@PathVariable String dateEnd){
        //CustomerEntity cs = customerRepository.findBycustomerid(cus_id);
        HotelEntity ht = hotelRepository.findByName(hotel);
        PromotionEntity pr = promotionRepository.findBydetail(detail);
        ReservationsEntity rs = new ReservationsEntity();
        rs.setPromotiontype(pr);
        rs.setHoteltype(ht);
        //rs.setCustomertype(cs);
        rs.setNumberofpeople(numberofpeople);
        rs.setNumberofroom(numberofroom);
        rs.setDateStart(dateStart);
        rs.setDateEnd(dateEnd);

        return reservationsRepository.save(rs);
    }
    @GetMapping(path = "/promotion/getdata/{hotelSelect}/{roomTypeSelect}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<PromotionEntity> getPromotionFromHotel(@PathVariable String hotelSelect, @PathVariable String roomTypeSelect) {
        HotelEntity hotel = hotelRepository.findByhotelNameEng(hotelSelect);
        RoomTypeEntity rt = roomTypeRepository.findByroomType(roomTypeSelect);
        Collection<PromotionEntity> R = promotionRepository.findByhotelEntityAndRoomTypeEntity(hotel,rt);
        System.out.println(R);
        return R.stream().collect(Collectors.toList());
    }

}
