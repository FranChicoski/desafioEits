import { Broker } from 'eits-ng2';
import { Component,OnInit, Input } from '@angular/core';
import { RouterModule, Routes, Router, ActivatedRoute, Params } from '@angular/router';
import {MdSnackBar} from '@angular/material';
import { Location } from '@angular/common';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { ExcluirConfirmComponent } from './../../../controls/excluir/excluir-confirm.component';

@Component({
    selector:'categoriaDetail',
    templateUrl:'./detail.component.html',
})
export class DetailCategoriaComponent implements OnInit{
/*---------------Atributes-----------------------------------------*/
  @Input() categoria : any = {};

  prioridades = [
    { value: '0', viewValue: 'Imediata' },
    { value: '1', viewValue: 'Urgente' },
    { value: '2', viewValue: 'Alta' },
    { value: '3', viewValue: 'Normal' },
    { value: '4', viewValue: 'Baixa' }
  ];

  
/*---------------------Constructor & Beginner ---------------------*/
  constructor(public activatedRoute: ActivatedRoute, public snackBar: MdSnackBar, 
            public location: Location, public dialog: MdDialog) { }
/**
 *
 */
  ngOnInit()
  {

         let id: number = this.activatedRoute.snapshot.params['categoriaId'];
         if(id){
             this.findCategoriaById(id);
         }
   }

   /*--------------------Messages---------------------------------*/
    openSnackBar(message: string)
    {
      this.snackBar.open(message,"ok",{
        duration: 5000
        });
    }

    /*----------------------Methods--------------------------------*/
    
    goBack(): void 
    {
      this.location.back();
    }
    
    
    /**
   *
   * @param categoriaId
   */
  public findCategoriaById(id)
  {
    Broker.of("categoriaService").promise("findCategoriaById", + id)
    .then((result) => {
        this.categoria = result;
    }).catch((exception) =>
    {
        this.openSnackBar.arguments(exception.message);
    })
  }


    public openExcludeDialog(): void {
      let dialogRef = this.dialog.open(ExcluirConfirmComponent, {
          width: '500px',
          data: {categoria: this.categoria}
      });
 /*     dialogRef.afterClosed().subscribe(result =>{
          if (result){
              this.categoria = result;
          }
      });*/
    }
}

