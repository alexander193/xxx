import { Component, OnInit } from '@angular/core';
import { ViewPromotionService } from '../service/view-promotion.service';
import {SelectionModel} from '@angular/cdk/collections';
import {MatTableDataSource} from '@angular/material';
import { HttpClient} from '@angular/common/http';


export interface ViewPromotionElement {
no: number;
name: string;
dateStart : String;
}

@Component({
  selector: 'app-view-promotion',
  templateUrl: './view-promotion.component.html',
  styleUrls: ['./view-promotion.component.css']
})
export class ViewPromotionComponent implements OnInit {
  displayedColumns: string[] = ['select', 'no', 'hotelName', 'roomType', 'promotionType', 'detail','dateStart','dateEnd'];
  promotions: Array<any>;
  selection = new SelectionModel<ViewPromotionElement>(true, []);

  updatePromotion: any = {
    hotelSelect: null,
    roomTypeSelect: null,
    promotionTpyeSelect: null,
    discountInput: null,
    dateStartInput: null,
    dateEndInput: null,
    detailInput: null,
    promotionId: null
  }

  private id: number;
  private sub: any;

  constructor(private viewPromotionService: ViewPromotionService , private httpClient: HttpClient) { }

  ngOnInit() {
    this.viewPromotionService.getPromotion().subscribe(data => {
      this.promotions = data;
      console.log(this.promotions);
    });
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.promotions.length;
    if ( numSelected !== 0) {
      this.promotions[numSelected - 1].promotionId = this.id;
    }
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected() ?
    this.selection.clear() :
    this.promotions.forEach(row => this.selection.select(row));
  }

  selectRow(row){
    this.selection.toggle(row);
    this.updatePromotion.hotelSelect = row.hotelEntity.hotelNameEng;
    this.updatePromotion.roomTypeSelect = row.roomTypeEntity.roomType;
    this.updatePromotion.promotionTpyeSelect = row.promotionTypeEntity.promotionType;
    this.updatePromotion.discountInput = row.discount;
    this.updatePromotion.dateStartInput = new Date(row.dateStart);
    this.updatePromotion.dateEndInput = new Date(row.dateEnd);
    this.updatePromotion.detailInput = row.detail;
    this.updatePromotion.promotionId = row.promotionId;
    console.log(this.updatePromotion);
  }
  update(){
    this.httpClient.put('http://localhost:8080/promotion/update/' + this.updatePromotion.hotelSelect + '/' + this.updatePromotion.roomTypeSelect + '/' + this.updatePromotion.promotionTpyeSelect+ '/' + this.updatePromotion.discountInput+ '/' + this.updatePromotion.dateStartInput+ '/' + this.updatePromotion.dateEndInput+ '/' + this.updatePromotion.detailInput+ '/' + this.updatePromotion.promotionId, this.updatePromotion)
         .subscribe(
             data => {
                 console.log('UPDATE Request is successful', data);
             },
             error => {
                  console.log('Error', error);
            }
    );
  }
  delete(){
    this.httpClient.delete('http://localhost:8080/promotion/'+ this.updatePromotion.promotionId)
         .subscribe(
             data => {
                 console.log('DELETE Request is successful', data);
             },
             error => {
                  console.log('Error', error);
            }
    );
  }
}

