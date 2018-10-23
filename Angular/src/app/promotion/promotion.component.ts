import { Component, OnInit } from '@angular/core';
import { PromotionService } from '../service/promotion.service';
import { HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.css']
})
export class PromotionComponent implements OnInit {
  hotels: Array<any>;
  roomTypes: Array<any>;
  promotionTypes: Array<any>;
  views: any = {
    hotelSelect: null,
    roomTypeSelect: null,
    promotionTpyeSelect: null,
    discountInput: null,
    dateStartInput: null,
    dateEndInput: null,
    detailInput: null
  };

  constructor(private promotionService: PromotionService , private httpClient: HttpClient) { }

  ngOnInit() {
    this.promotionService.getHotelNameEng().subscribe(data => {
      this.hotels = data;
      console.log(this.hotels);
    });

    this.promotionService.getPromotionType().subscribe(data => {
      this.promotionTypes = data;
      console.log(this.promotionTypes);
    });
  }

  save() {
    if (this.views.hotelSelect == null) {
      alert('กรุณาเลือกโรงแรม');
    }
    else if (this.views.roomTypeSelect == null) {
      alert('กรุณาเลือกประเภทห้องพัก');
    }
    else if(this.views.promotionTpyeSelect == null) {
      alert('กรุณาเลือกประเภทของโปรโมชั่น');
    }
    else if(this.views.dateStartInput == null) {
      alert('กรุณาระบุวันที่เริ่มโปรโมชั่น');
    }
    else if(this.views.dateEndInput == null) {
      alert('กรุณาระบุวันที่สิ้นสุดโปรโมชั่น');
    }else {
      if(this.views.promotionTpyeSelect == 'Discount') {
        if(this.views.discountInput == null ||this.views.discountInput == ''){
          alert('กรุณากรอก%ลดราคา');
        }
        else{
        if(this.views.detailInput == ''){
          this.views.detailInput = 'null';
        }
          this.save_func();
        }
      }
      else{
        this.views.discountInput = 0;
        if(this.views.detailInput == ''){
          this.views.detailInput = 'null';
        }
        this.save_func();
      }
    }
  }
  save_func(){

    this.httpClient.post('http://localhost:8080/promotion/' + this.views.hotelSelect + '/' + this.views.roomTypeSelect + '/' + this.views.promotionTpyeSelect+ '/' + this.views.discountInput+ '/' + this.views.dateStartInput+ '/' + this.views.dateEndInput+ '/' + this.views.detailInput, this.views)
         .subscribe(
             data => {
                 console.log('PUT Request is successful', data);
             },
             error => {
                  console.log('Rrror', error);
            }
    );
  }
  update_type(){
      this.promotionService.getRoomId(this.views.hotelSelect).subscribe(data => {
      this.roomTypes = data;
      console.log(this.roomTypes);
    });
  }
}
