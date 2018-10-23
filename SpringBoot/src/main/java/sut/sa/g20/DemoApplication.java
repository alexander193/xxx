package sut.sa.g20;
        import org.springframework.boot.ApplicationRunner;
        import org.springframework.context.annotation.Bean;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import sut.sa.g20.entity.*;
        import sut.sa.g20.repository.*;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;

        import java.text.SimpleDateFormat;
        import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner init(HotelRepository hotelRepository, RoomRepository roomRepository, PromotionRepository promotionRepository,
                           PromotionTypeRepository promotionTypeRepository, RoomTypeRepository roomTypeRepository,
                           ProvinceRepository provinceRepository, RoomStatusRepository roomStatusRepository,
                           CountryCodeRepository countryCodeRepository, CustomerRepository customerRepository,
                           SexRepository sexRepository, TitleNameRepository titleNameRepository,
                           FurnitureRepository furnitureRepository, MemberHotelRepository memberHotelRepository,
                           RoomTypeFurnitureManyToManyRepository roomTypeFurnitureManyToManyRepository){
        return args -> {

            Stream.of("ว่าง","จอง","พัก").forEach(roomstatus ->{
                RoomStatusEntity roomStatus = new RoomStatusEntity();
                roomStatus.setRoomStatus(roomstatus);
                roomStatusRepository.save(roomStatus);
            });

            Stream.of("กรุงเทพมหานคร","กระบี่","กาญจนบุรี","กาฬสินธุ์","กำแพงเพชร","ขอนแก่น","จันทบุรี","ฉะเชิงเทรา","ชลบุรี","ชัยนาท"
                    ,"ชัยภูมิ","ชุมพร","เชียงราย","เชียงใหม่","ตรัง","ตราด","ตาก","นครนายก","นครปฐม","นครพนม","นครราชสีมา","นครศรีธรรมราช"
                    ,"นครสวรรค์","นนทบุรี","นราธิวาส","น่าน","บึงกาฬ","บุรีรัมย์","ปทุมธานี","ประจวบคีรีขันธ์","ปราจีนบุรี","ปัตตานี","พระนครศรีอยุธยา"
                    ,"พังงา","พัทลุง","พิจิตร","พิษณุโลก","เพชรบุรี","เพชรบูรณ์","แพร่","พะเยา","ภูเก็ต","มหาสารคาม","มุกดาหาร","แม่ฮ่องสอน"
                    ,"ยะลา","ยโสธร","ร้อยเอ็ด","ระนอง","ระยอง","ราชบุรี","ลพบุรี","ลำปาง","ลำพูน","เลย","ศรีสะเกษ","สกลนคร","สงขลา","สตูล"
                    ,"สมุทรปราการ","สมุทรสงคราม","สมุทรสาคร","สระแก้ว","สระบุรี","สิงห์บุรี","สุโขทัย","สุพรรณบุรี","สุราษฎร์ธานี","สุรินทร์","หนองคาย"
                    ,"หนองบัวลำภู","อ่างทอง","อุดรธานี","อุทัยธานี","อุตรดิตถ์","อุบลราชธานี","อำนาจเจริญ").forEach(provinces -> {
                ProvinceEntity province = new ProvinceEntity();
                province.setProvinceName(provinces);
                provinceRepository.save(province);
            });

            //Create Furniture
            Stream.of("Table","OfficeTable","Microwave","Sofa").forEach(furniture -> {
                FurnitureEntity fr = new FurnitureEntity(furniture);
                furnitureRepository.save(fr);
            });

            //Create Member
            MemberHotelEntity mem = new MemberHotelEntity();
            mem.setMemberHotelName("Aphirat");
            mem.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem);

            MemberHotelEntity mem2 = new MemberHotelEntity();
            mem2.setMemberHotelName("Pitchayut");
            mem2.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem2);

            MemberHotelEntity mem3 = new MemberHotelEntity();
            mem3.setMemberHotelName("Tah");
            mem3.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem3);

            MemberHotelEntity mem4 = new MemberHotelEntity();
            mem4.setMemberHotelName("Cheese");
            mem4.setMemberHotelPassword(1234L);
            memberHotelRepository.save(mem4);

            Stream.of("Sukkhamas Pirom Hotel","Grand Ever 9","Kantary Hotel","The Beverly Hills Hotel").forEach(hotelName -> {
                if (hotelName.equals("Sukkhamas Pirom Hotel")) {
                    HotelEntity hotelEntity = new HotelEntity();
                    MemberHotelEntity m = memberHotelRepository.findByName("Pitchayut");
                    hotelEntity.setMemberHotel(m);
                    hotelEntity.setHotelNameEng(hotelName);
                    hotelEntity.setHotelNameThai("โรงแรมสุขมาศก์ภิรมย์");
                    hotelEntity.setHouseNo("541");
                    hotelEntity.setVillageNo(2);
                    hotelEntity.setRoad("ผังเมือง");
                    hotelEntity.setSubDistrictSubArea("บ้านเกาะ");
                    hotelEntity.setDistrictArea("เมือง");
                    ProvinceEntity province = provinceRepository.findByprovinceName("เลย");
                    hotelEntity.setProvinceEntity(province);
                    hotelEntity.setPostCode(30000);
                    hotelEntity.setMobilePhone("0968864969");
                    hotelEntity.setPhone("044342159");
                    hotelEntity.setFax("044342169");
                    hotelRepository.save(hotelEntity);
                }
                if(hotelName.equals("Grand Ever 9")){
                    HotelEntity hotelEntity = new HotelEntity();
                    MemberHotelEntity m = memberHotelRepository.findByName("Aphirat");
                    hotelEntity.setMemberHotel(m);
                    hotelEntity.setHotelNameEng(hotelName);
                    hotelEntity.setHotelNameThai("โรงแรมสุขมาศก์ภิรมย์");
                    hotelEntity.setHouseNo("541");
                    hotelEntity.setVillageNo(2);
                    hotelEntity.setRoad("ผังเมือง");
                    hotelEntity.setSubDistrictSubArea("บ้านเกาะ");
                    hotelEntity.setDistrictArea("เมือง");
                    ProvinceEntity province = provinceRepository.findByprovinceName("นครราชสีมา");
                    hotelEntity.setProvinceEntity(province);
                    hotelEntity.setPostCode(30000);
                    hotelEntity.setMobilePhone("0968864969");
                    hotelEntity.setPhone("044342159");
                    hotelEntity.setFax("044342169");
                    hotelRepository.save(hotelEntity);
                }
                if(hotelName.equals("Kantary Hotel")){
                    HotelEntity hotelEntity = new HotelEntity();
                    MemberHotelEntity m = memberHotelRepository.findByName("Cheese");
                    hotelEntity.setMemberHotel(m);
                    hotelEntity.setHotelNameEng(hotelName);
                    hotelEntity.setHotelNameThai("โรงแรมสุขมาศก์ภิรมย์");
                    hotelEntity.setHouseNo("541");
                    hotelEntity.setVillageNo(2);
                    hotelEntity.setRoad("ผังเมือง");
                    hotelEntity.setSubDistrictSubArea("บ้านเกาะ");
                    hotelEntity.setDistrictArea("เมือง");
                    ProvinceEntity province = provinceRepository.findByprovinceName("นครราชสีมา");
                    hotelEntity.setProvinceEntity(province);
                    hotelEntity.setPostCode(30000);
                    hotelEntity.setMobilePhone("0968864969");
                    hotelEntity.setPhone("044342159");
                    hotelEntity.setFax("044342169");
                    hotelRepository.save(hotelEntity);
                }
                if(hotelName.equals("The Beverly Hills Hotel")){
                    HotelEntity hotelEntity = new HotelEntity();
                    MemberHotelEntity m = memberHotelRepository.findByName("Tah");
                    hotelEntity.setMemberHotel(m);
                    hotelEntity.setHotelNameEng(hotelName);
                    hotelEntity.setHotelNameThai("โรงแรมสุขมาศก์ภิรมย์");
                    hotelEntity.setHouseNo("541");
                    hotelEntity.setVillageNo(2);
                    hotelEntity.setRoad("ผังเมือง");
                    hotelEntity.setSubDistrictSubArea("บ้านเกาะ");
                    hotelEntity.setDistrictArea("เมือง");
                    ProvinceEntity province = provinceRepository.findByprovinceName("น่าน");
                    hotelEntity.setProvinceEntity(province);
                    hotelEntity.setPostCode(30000);
                    hotelEntity.setMobilePhone("0968864969");
                    hotelEntity.setPhone("044342159");
                    hotelEntity.setFax("044342169");
                    hotelRepository.save(hotelEntity);
                }
            });

            Stream.of("Standard","Suite","Superior","Deluxe").forEach(roomType -> {
                if (roomType.equals("Standard")) {
                    RoomTypeEntity type = new RoomTypeEntity();
                    type.setRoomType(roomType);
                    type.setNumberOfBed(1);
                    type.setBedType("Single");
                    type.setMaxPeople(2);
                    roomTypeRepository.save(type);
                }
                if(roomType.equals("Suite")){
                    RoomTypeEntity type = new RoomTypeEntity();
                    type.setRoomType(roomType);
                    type.setNumberOfBed(1);
                    type.setBedType("Double");
                    type.setMaxPeople(2);
                    roomTypeRepository.save(type);
                }
                if(roomType.equals("Superior")){
                    RoomTypeEntity type = new RoomTypeEntity();
                    type.setRoomType(roomType);
                    type.setNumberOfBed(2);
                    type.setBedType("Double");
                    type.setMaxPeople(4);
                    roomTypeRepository.save(type);
                }
                if(roomType.equals("Deluxe")){
                    RoomTypeEntity type = new RoomTypeEntity();
                    type.setRoomType(roomType);
                    type.setNumberOfBed(2);
                    type.setBedType("Double");
                    type.setMaxPeople(4);
                    roomTypeRepository.save(type);
                }
            });

            //Create RoomTypeNameManyToMany
            Stream.of("Table","OfficeTable","Microwave","Sofa").forEach(furniture -> {
                Stream.of("Standard","Suite","Deluxe","Superior").forEach(roomType -> {
                    FurnitureEntity fr = furnitureRepository.findByName(furniture);
                    RoomTypeEntity rt = roomTypeRepository.findByName(roomType);
                    RoomTypeFurnitureManyToManyEntity rtf = new RoomTypeFurnitureManyToManyEntity(fr,rt);
                    roomTypeFurnitureManyToManyRepository.save(rtf);
                });

            });



            Stream.of("Discount","Free Breakfast","Additional Services").forEach(promotionType -> {
                if (promotionType.equals("Discount")) {
                    PromotionTypeEntity type = new PromotionTypeEntity();
                    type.setPromotionType(promotionType);
                    promotionTypeRepository.save(type);
                }
                if(promotionType.equals("Free Breakfast")){
                    PromotionTypeEntity type = new PromotionTypeEntity();
                    type.setPromotionType(promotionType);
                    promotionTypeRepository.save(type);
                }
                if(promotionType.equals("Additional Services")){
                    PromotionTypeEntity type = new PromotionTypeEntity();
                    type.setPromotionType(promotionType);
                    promotionTypeRepository.save(type);
                }
            });
            Stream.of("Sukkhamas Pirom Hotel","Grand Ever 9","Kantary Hotel","The Beverly Hills Hotel").forEach(hotelName -> {
                Stream.of("Standard","Suite","Superior","Deluxe").forEach(roomType -> {
                    if(hotelName.equals("Sukkhamas Pirom Hotel") && roomType.equals("Standard")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(101);
                        room.setRoomPrice(650);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Sukkhamas Pirom Hotel") && roomType.equals("Suite")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(201);
                        room.setRoomPrice(1000);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Sukkhamas Pirom Hotel") && roomType.equals("Superior")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(301);
                        room.setRoomPrice(1250);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Sukkhamas Pirom Hotel") && roomType.equals("Deluxe")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(401);
                        room.setRoomPrice(1500);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Grand Ever 9") && roomType.equals("Standard")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(1);
                        room.setRoomPrice(700);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Grand Ever 9") && roomType.equals("Suite")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(2);
                        room.setRoomPrice(1400);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Grand Ever 9") && roomType.equals("Superior")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(3);
                        room.setRoomPrice(1300);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Kantary Hotel") && roomType.equals("Standard")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(101);
                        room.setRoomPrice(500);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Kantary Hotel") && roomType.equals("Suite")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(201);
                        room.setRoomPrice(1000);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("Kantary Hotel") && roomType.equals("Deluxe")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(301);
                        room.setRoomPrice(1700);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("The Beverly Hills Hotel") && roomType.equals("Standard")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(1);
                        room.setRoomPrice(450);
                        roomRepository.save(room);
                    }
                    if(hotelName.equals("The Beverly Hills Hotel") && roomType.equals("Suite")){
                        RoomEntity room = new RoomEntity();
                        HotelEntity h = hotelRepository.findByhotelNameEng(hotelName);
                        room.setHotel(h);
                        RoomTypeEntity type = roomTypeRepository.findByroomType(roomType);
                        room.setRoomTypeEntity(type);
                        RoomStatusEntity status = roomStatusRepository.findByroomStatus("ว่าง");
                        room.setRoomStatusEntity(status);
                        room.setRoomNumber(2);
                        room.setRoomPrice(1000);
                        roomRepository.save(room);
                    }
                });
            });

            SimpleDateFormat formatter5 = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
            PromotionEntity p1 = new PromotionEntity();
            HotelEntity h1 = hotelRepository.findByhotelNameEng("Sukkhamas Pirom Hotel");
            p1.setHotelEntity(h1);
            RoomTypeEntity type1 = roomTypeRepository.findByroomType("Standard");
            p1.setRoomTypeEntity(type1);
            PromotionTypeEntity promoType1 = promotionTypeRepository.findByPromotionType("Discount");
            p1.setPromotionTypeEntity(promoType1);
            p1.setDiscount(5);
            p1.setDateStart(formatter5.parse("Thu, Oct 18 2018 00:00:00"));
            p1.setDateEnd(formatter5.parse("Fri, Oct 19 2018 00:00:00"));
            p1.setDetail("ลดราคาห้องพักสุดหรู");
            promotionRepository.save(p1);

            PromotionEntity p2 = new PromotionEntity();
            HotelEntity h2 = hotelRepository.findByhotelNameEng("Kantary Hotel");
            p2.setHotelEntity(h2);
            RoomTypeEntity type2 = roomTypeRepository.findByroomType("Deluxe");
            p2.setRoomTypeEntity(type2);
            PromotionTypeEntity promoType2 = promotionTypeRepository.findByPromotionType("Free Breakfast");
            p2.setPromotionTypeEntity(promoType2);
            p2.setDateStart(formatter5.parse("Thu, Oct 25 2018 00:00:00"));
            p2.setDateEnd(formatter5.parse("Fri, Oct 26 2018 00:00:00"));
            p2.setDetail("ฟรีอาหารเช้า");
            promotionRepository.save(p2);

            //Insert Country_code
            //NORTH AMERICA
            CountryCodeEntity Alaska = new CountryCodeEntity("+1+907 (อะเเลสกา)");
            countryCodeRepository.save(Alaska);
            CountryCodeEntity Canada = new CountryCodeEntity("+1 (แคนาดา)");
            countryCodeRepository.save(Canada);
            CountryCodeEntity Hawaii = new CountryCodeEntity("+1+808 (ฮาวาย)");
            countryCodeRepository.save(Hawaii);
            CountryCodeEntity USA = new CountryCodeEntity("+1 (สหรัฐอเมริกา)");
            countryCodeRepository.save(USA);
            //ASIA
            CountryCodeEntity Bangladesh = new CountryCodeEntity("+880 (บังคลาเทศ)");
            countryCodeRepository.save(Bangladesh);
            CountryCodeEntity Bhutan = new CountryCodeEntity("+975 (ภูฏาน)");
            countryCodeRepository.save(Bhutan);
            CountryCodeEntity Brunei = new CountryCodeEntity("+673 (บรูไน)");
            countryCodeRepository.save(Brunei);
            CountryCodeEntity Cambodia = new CountryCodeEntity("+855 (กัมพูชา)");
            countryCodeRepository.save(Cambodia);
            CountryCodeEntity China = new CountryCodeEntity("+86 (จีน)");
            countryCodeRepository.save(China);
            CountryCodeEntity Laos = new CountryCodeEntity("+856 (ลาว)");
            countryCodeRepository.save(Laos);
            CountryCodeEntity Malaysia = new CountryCodeEntity("+856 (มาเลเซีย)");
            countryCodeRepository.save(Malaysia);
            CountryCodeEntity Hongkong = new CountryCodeEntity("+852 (ฮ่องกง)");
            countryCodeRepository.save(Hongkong);
            CountryCodeEntity India = new CountryCodeEntity("+91 (อินเดีย)");
            countryCodeRepository.save(India);
            CountryCodeEntity Indonesia = new CountryCodeEntity("+62 (อินโดนีเซีย)");
            countryCodeRepository.save(Indonesia);
            CountryCodeEntity Japan = new CountryCodeEntity("+81 (ญี่ปุ่น)");
            countryCodeRepository.save(Japan);
            CountryCodeEntity Koreasouth = new CountryCodeEntity("+82 (เกาหลีใต้)");
            countryCodeRepository.save(Koreasouth);
            CountryCodeEntity Macao = new CountryCodeEntity("+853 (มาเก๊า)");
            countryCodeRepository.save(Macao);
            CountryCodeEntity Maldives = new CountryCodeEntity("+960 (มัลดีฟส์)");
            countryCodeRepository.save(Maldives);
            CountryCodeEntity Mongolia = new CountryCodeEntity("+976 (มองโกเลีย)");
            countryCodeRepository.save(Mongolia);
            CountryCodeEntity Myanmar = new CountryCodeEntity("+95 (พม่า)");
            countryCodeRepository.save(Myanmar);
            CountryCodeEntity Nepal = new CountryCodeEntity("+977 (เนปาล)");
            countryCodeRepository.save(Nepal);
            CountryCodeEntity Pakistan = new CountryCodeEntity("+92 (ปากีสถาน)");
            countryCodeRepository.save(Pakistan);
            CountryCodeEntity Philippines = new CountryCodeEntity("+63 (ฟิลิปปินส์)");
            countryCodeRepository.save(Philippines);
            CountryCodeEntity Singapore = new CountryCodeEntity("+65 (สิงคโปร์)");
            countryCodeRepository.save(Singapore);
            CountryCodeEntity Srilanka = new CountryCodeEntity("+94 (ศรีลังกา)");
            countryCodeRepository.save(Srilanka);
            CountryCodeEntity Thailand = new CountryCodeEntity("+66 (ไทย)");
            countryCodeRepository.save(Thailand);
            CountryCodeEntity Taiwan = new CountryCodeEntity("+886 (ไต้หวัน)");
            countryCodeRepository.save(Taiwan);
            CountryCodeEntity Vietnam = new CountryCodeEntity("+84 (เวียดนาม)");
            countryCodeRepository.save(Vietnam);

            // Australia, New Zealand
            CountryCodeEntity Australia = new CountryCodeEntity("+61 (ออสเตรเลีย)");
            countryCodeRepository.save(Australia);
            CountryCodeEntity Newzealand = new CountryCodeEntity("+64 (นิวซีแลนด์)");
            countryCodeRepository.save(Newzealand);
            //PACIFIC OCEAN
            CountryCodeEntity AmericanSamoa = new CountryCodeEntity("+684 (อเมริกันซามัว)");
            countryCodeRepository.save(AmericanSamoa);
            CountryCodeEntity Fiji = new CountryCodeEntity("+679 (ฟีจี)");
            countryCodeRepository.save(Fiji);
            CountryCodeEntity Frenchpolynesia = new CountryCodeEntity("+689 (เฟรนช์พอลินีเชีย)");
            countryCodeRepository.save(Frenchpolynesia);
            CountryCodeEntity Guam = new CountryCodeEntity("+1+671 (กวม)");
            countryCodeRepository.save(Guam);
            CountryCodeEntity Micronesia = new CountryCodeEntity("+691 (ไมโครนีเซีย)");
            countryCodeRepository.save(Micronesia);
            CountryCodeEntity Nauru = new CountryCodeEntity("+674 (นาอูรู)");
            countryCodeRepository.save(Nauru);
            CountryCodeEntity Newcaledonia = new CountryCodeEntity("+687 (นิวแคลิโดเนีย)");
            countryCodeRepository.save(Newcaledonia);
            CountryCodeEntity Papuanewguinea = new CountryCodeEntity("+675 (ปาปัวนิวกินี)");
            countryCodeRepository.save(Papuanewguinea);
            CountryCodeEntity Saipan = new CountryCodeEntity("+1+670 (เกาะไซปัน)");
            countryCodeRepository.save(Saipan);
            CountryCodeEntity Tokelau = new CountryCodeEntity("+690 (โทเคอเลา)");
            countryCodeRepository.save(Tokelau);
            CountryCodeEntity Tonga = new CountryCodeEntity("+676 (ตองงา)");
            countryCodeRepository.save(Tonga);
            CountryCodeEntity Vanuatu = new CountryCodeEntity("+678 (วานูอาตู)");
            countryCodeRepository.save(Vanuatu);
            CountryCodeEntity Westernsamoa = new CountryCodeEntity("+685 (ซามัว)");
            countryCodeRepository.save(Westernsamoa);
            //EUROPE
            CountryCodeEntity Albania = new CountryCodeEntity("+355 (แอลเบเนีย)");
            countryCodeRepository.save(Albania);
            CountryCodeEntity Andorra = new CountryCodeEntity("+376 (อันดอร์รา)");
            countryCodeRepository.save(Andorra);
            CountryCodeEntity Armenia = new CountryCodeEntity("+374 (อาร์มีเนีย)");
            countryCodeRepository.save(Armenia);
            CountryCodeEntity Austria = new CountryCodeEntity("+43 (ออสเตรีย)");
            countryCodeRepository.save(Austria);
            CountryCodeEntity Azerbaijan = new CountryCodeEntity("+994 (อาเซอร์ไบจาน)");
            countryCodeRepository.save(Azerbaijan);
            CountryCodeEntity Belarus = new CountryCodeEntity("+375 (เบลารุส)");
            countryCodeRepository.save(Belarus);
            CountryCodeEntity Belgium = new CountryCodeEntity("+32 (เบลเยียม)");
            countryCodeRepository.save(Belgium);
            CountryCodeEntity BosniaHerzegovina = new CountryCodeEntity("+387 (บอสเนียและเฮอร์เซโกวีนา)");
            countryCodeRepository.save(BosniaHerzegovina);
            CountryCodeEntity Bulgaria = new CountryCodeEntity("+359 (บัลแกเรีย)");
            countryCodeRepository.save(Bulgaria);
            CountryCodeEntity Croatia = new CountryCodeEntity("+385 (โครเอเชีย)");
            countryCodeRepository.save(Croatia);
            CountryCodeEntity czech = new CountryCodeEntity("+420 (เช็กเกีย)");
            countryCodeRepository.save(czech);
            CountryCodeEntity Denmark = new CountryCodeEntity("+45 (เดนมาร์ก)");
            countryCodeRepository.save(Denmark);
            CountryCodeEntity Estonia = new CountryCodeEntity("+372 (เอสโตเนีย)");
            countryCodeRepository.save(Estonia);
            CountryCodeEntity IaroeIslands = new CountryCodeEntity("+298 (เอสโตเนีย)");
            countryCodeRepository.save(IaroeIslands);
            CountryCodeEntity Finland = new CountryCodeEntity("+358 (ฟินแลนด์)");
            countryCodeRepository.save(Finland);

            //Insert SexEntity
            SexEntity m = new SexEntity("ชาย");
            sexRepository.save(m);
            SexEntity fm = new SexEntity("หญิง");
            sexRepository.save(fm);
            SexEntity nfmm = new SexEntity("-ไม่ระบุ-");
            sexRepository.save(nfmm);

            //Insert Title_name
            TitleNameEntity a = new TitleNameEntity("นาย");
            titleNameRepository.save(a);
            TitleNameEntity b = new TitleNameEntity("นาง");
            titleNameRepository.save(b);
            TitleNameEntity c = new TitleNameEntity("นางสาว");
            titleNameRepository.save(c);
        };
    }
}
