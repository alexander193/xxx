import { Component, OnInit } from '@angular/core';
import { ReservationsService } from '../service/reservations.service';
import { HttpClient} from '@angular/common/http';
@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  hotel : Array<any>;
  promotions : Array<any>;
  roomTypes : Array<any>;
  views : any = {
    hotelSelect: '',
    roomTypeSelect: '',
    promotionsSelect: '',
    numberofpeople: '',
    numberofroom: '',
    dateStart: '',
    dateEnd: ''

  };
  constructor(private reservationsService: ReservationsService , private httpClient: HttpClient) { }

  ngOnInit() {
    this.reservationsService.getHotel_name().subscribe(data => {
      this.hotel = data;
      console.log(this.hotel);
    });
    this.reservationsService.getpromotionsType().subscribe(data => {
      this.promotions = data;
      console.log(this.promotions);
    });
  }
  save() {
   if(this.views.hotelSelect === '') {
      alert('กรุณาเลือกโรงแรม');
    }
    else if(this.views.hotelSelect === '') {
      alert('กรุณาเลือกโปรโมชั่น');
    }
   else if(this.views.phoneInput === '') {
     alert('กรุณากรอกเบอร์โทร');
   }
    else if (this.views.sexSelect === '') {
      alert('กรุณาเลือกเพศ');
    }
   else if(this.views.adderssInput === '') {
     alert('กรุณากรอกที่อยู่')
   }
    else if (this.views.timeworkingSelect === '') {
      alert('กรุณาเลือกระยะเวลาการทำงาน');
    }
    else if(this.views.typeworkingSelect === '') {
      alert('กรุณาเลือกประเภทงาน');

    }else {
      alert('http://localhost:8080/Reservations/'+ this.views.hotelSelect + '/' + this.views.roomTypeSelect  +'/'+ this.views.promotionsSelect + '/'+ this.views.numberofpeople + '/' +  this.views.numberofroom + '/' + this.views.dateStart + '/' + this.views.dateEnd);
      alert(this.views.hotelSelect);
      alert(this.views.roomTypeSelect);
      alert(this.views.promotionsSelect);
      alert(this.views.numberofpeople);
      alert(this.views.numberofroom);
      this.httpClient.get('http://localhost:8080/Reservations/'+ this.views.hotelSelect + '/' + this.views.roomTypeSelect  +'/'+ this.views.promotionsSelect + '/'+ this.views.numberofpeople + '/' +  this.views.numberofroom + '/' + this.views.dateStart + '/' + this.views.dateEnd, this.views)

        .subscribe(
          data => {
              console.log('PUT Request is successful', data);
          },
          error => {
              console.log('Rrror', error);
          }
      );

    }
  }
  update_type(){
    this.reservationsService.getRoomId(this.views.hotelSelect).subscribe(data => {
      this.roomTypes = data;
      console.log(this.roomTypes);
    });
  }

  update_promotion(){
    alert(this.views.hotelSelect);
    alert(this.views.roomTypeSelect);
    console.log(this.views.hotelSelect);
    console.log(this.views.roomTypeSelect);
    this.reservationsService.getPromotion(this.views.hotelSelect,this.views.roomTypeSelect).subscribe(data => {
      this.promotions = data;
      console.log(this.promotions);
    });
  }

}
