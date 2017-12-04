import { ExcluirConfirmComponent } from './../../../controls/excluir/excluir-confirm.component';
import { RouterModule, Routes, Router, ActivatedRoute, Params } from '@angular/router';
import { MdDialog, MdDialogRef, MD_DIALOG_DATA } from '@angular/material';
import { Component, Input, OnInit } from "@angular/core";
import {MdDatepickerModule} from '@angular/material';
import { ITdDataTableColumn } from '@covalent/core';
import { TdDialogService } from '@covalent/core';
import {MdSnackBar} from '@angular/material';
import { Broker } from "eits-ng2";


@Component({
  selector: 'categoriaList',
  templateUrl:'./list.component.html'
 // styleUrls: ['./list.component.css']
})
export class ListCategoriaComponent implements OnInit{
 /*------------------Attributes-------------------- */
 @Input() categorias: any[] = [];

  @Input() categoria: any = {};

  filtros: any = {
    filter: null,
    dataCriacaoStart: null,
    dataCriacaoEnd: null,
    prioridade: null,
    pageable: null
  };


prioridades = [
  { value: '0', viewValue: 'Imediata' },
  { value: '1', viewValue: 'Urgente' },
  { value: '2', viewValue: 'Alta' },
  { value: '3', viewValue: 'Normal' },
  { value: '4' , viewValue: 'Baixa' }
];

/*--------------------Constructor---------------------------------*/

  constructor(public activatedRoute: ActivatedRoute , private router: Router,
        public snackBar: MdSnackBar,public dialog: MdDialog){}


    ngOnInit(){
            this.listCategoriasByFilters(null,null,null, null,null);
    }
   
 /*--------------------Messages---------------------------------*/
    openSnackBar(message: string)
      {
        this.snackBar.open(message,"ok",{
          duration: 5000
          });
    }


/*-----------------------Methods------------------*/
/**
 * Chamando método da na CategoriaService do backEnd e passando os parâmetros da busca.
 */
    public listCategoriasByFilters(filter,dataCriacaoStart, dataCriacaoEnd, prioridade, pageable) {
        Broker.of("categoriaService").promise("listCategoriaByFilters", filter,dataCriacaoStart,
            dataCriacaoEnd, prioridade, pageable).then((result) => {
              console.log( result );
              this.categorias = result.content;
              this.cleanFilters();
            })
            .catch((exception) => {
                  console.log(exception.message);
            });
    }

/**
 * Recebendo os parâmetros do template e chamando método para filtrar.
 */
    public filterReciever()
    {
        console.log(this.filtros.filter);
        console.log(this.filtros.prioridade);
        console.log(this.filtros.pageable);
        this.listCategoriasByFilters(this.filtros.filter,this.filtros.dataCriacaoStart,
            this.filtros.dataCriacaoEnd, this.filtros.prioridade,this.filtros.pageable);
    }
     
    /**
     * Limpando filtros após preenchimento do usuário
     */
    public cleanFilters()
    {
        this.filtros.filter = null,
        this.filtros.dataCriacaoStart = null,
        this.filtros.dataCriacaoEnd = null,
        this.filtros.prioridade = null,
        this.filtros.pageable = null
    }

    openExcludeDialog(): void 
    {
      let dialogRef = this.dialog.open(ExcluirConfirmComponent, {
          width: '500px',
          data: {categoria: this.categoria}
      });
     /* dialogRef.afterClosed().subscribe(result =>{
          if (result){
              this.categoria = result;
          }
      });*/
    }

}
