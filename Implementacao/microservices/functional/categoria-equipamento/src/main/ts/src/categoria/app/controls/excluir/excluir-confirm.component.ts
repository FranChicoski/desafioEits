import { Broker } from 'eits-ng2';
import { Component, Inject, Input,  OnInit } from "@angular/core";
import { MdDialogRef, MD_DIALOG_DATA, MdSnackBar } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { RouterModule, Routes, Router, ActivatedRoute, Params } from '@angular/router';
import { DetailCategoriaComponent } from '../../views/categoria/detail/detail.component';



@Component({
    selector: 'excluir-dialog',
    templateUrl: './excluir-confirm.component.html'
})
export class ExcluirConfirmComponent implements OnInit
{
/*-----------Atributes-------------------*/      
    //@Input() categoria : any = {};       
    @Input() categoria : any = {};

  
/*-----------Constructors & Beginners ----------------*/ 
    /**
    * 
    * @param route 
    */
    constructor(@Inject(MD_DIALOG_DATA) public data: any, 
            private mdDialogRef : MdDialogRef<ExcluirConfirmComponent>,public snackBar: MdSnackBar,
            public activatedRoute: ActivatedRoute , private router: Router){}
 
    /**
     * 
     */
    ngOnInit(){
        this.categoria = this.data.categoria;
    }

 /*----------- Methods ----------------*/
    /**
     * 
     */
    onNoClick():void{
        this.mdDialogRef.close();
    }

    openSnackBar(message: string)
    {
      this.snackBar.open(message,"ok",{
        duration: 5000
        });
    }
    
    /**
     * 
     * @param id 
     */
    public deleteCategoriaById(id)
    {

        Broker.of("categoriaService").promise("deleteCategoria", id)
          .then((result) => {
                    this.openSnackBar("Categoria excluída com sucesso!");
                    this.mdDialogRef.close();
                    this.router.navigate([""]);
            }).catch((exception) =>{
                    this.openSnackBar.arguments(exception.message);
                    this.mdDialogRef.close();              
        })
    }

    
}