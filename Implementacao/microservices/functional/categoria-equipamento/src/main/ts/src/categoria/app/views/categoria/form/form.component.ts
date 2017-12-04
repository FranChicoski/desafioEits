import { Component, Input, OnInit } from "@angular/core";
import { FormsModule, NgForm, FormBuilder, FormControl, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes, Router, ActivatedRoute, Params } from '@angular/router';
import { Broker } from "eits-ng2";
import { MdSnackBar } from '@angular/material';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/operator/map';
/*import { Http } from '@angular/http';*/


@Component({
    selector: "categoriaForm",
    templateUrl: "./form.component.html"
    //styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit
{

    /*--------------Atributes---------------------*/
    categoria: any = {};

    prioridades = [
        { value: 'IMEDIATA', viewValue: 'Imediata' },
        { value: 'URGENTE', viewValue: 'Urgente' },
        { value: 'ALTA', viewValue: 'Alta' },
        { value: 'NORMAL', viewValue: 'Normal' },
        { value: 'BAIXA', viewValue: 'Baixa' }
    ];

    tituloCategoria = new FormControl('', [Validators.required, Validators.maxLength(120)]);
    prioridadeCategoria = new FormControl(this.prioridades[0].viewValue);
    descricaoCategoria = new FormControl('', Validators.required);

    categoriaForm: FormGroup;

    /*------------------Constructors & Beginners-------------- */
    constructor(public activatedRoute: ActivatedRoute, private router: Router, public snackBar: MdSnackBar, private fb: FormBuilder)
    {
        this.createForm();
    }

    ngOnInit()
    {
        let categoriaId: number = this.activatedRoute.snapshot.params['categoriaId'];
        if (categoriaId)
        {
            this.findCategoriaById(categoriaId);
        };
    }


    /*--------------------Mensagens------------------------*/

    validationMessages = {
        'titulo': {
            'required': 'Título é necessário',
            'maxLength': 'Título deve ser menor do que 120 caracteres'
        },
        'prioridade': {
            'required': 'Escolha uma prioridade'
        },
        'descricao': {
            'required': 'Descrição é necessária'
        }
    }


    /*------------------Methods-------------- */

    /**
     * 
     */
    createForm()
    {
        this.categoriaForm = this.fb.group({
            id: [{ value: null, disabled: true }],
            tituloCategoria: ['', [Validators.required, Validators.maxLength(120)]],  // <--- the FormControl called "name"
            prioridadeCategoria: ['', Validators.required],
            descricaoCategoria: ['', Validators.required]
        });
    }

    /**
     * 
     * @param message 
     */
    openSnackBar(message: string)
    {
        this.snackBar.open(message, "ok", {
            duration: 2000
        });
    }


    /**
     * 
     * @param categoriaForm 
     */
    onFormSubmit(categoriaForm)
    {
        if (this.categoriaForm.valid)
        {
            if (this.categoria.value == null && this.categoria.id != null)
            {
                console.log(this.categoria.id);
                this.updateCategoria(this.categoria);
            } else
            {
                this.categoria = this.categoriaForm.value;
                this.insertCategoria(this.categoria);
            }
        }
    }
    /**
     *
     * @param categoriaId
     */
    public findCategoriaById(id)
    {
        Broker.of("categoriaService").promise("findCategoriaById", + id)
            .then((result) =>
            {
                this.categoria = result;
            }).catch((exception) =>
            {
                this.openSnackBar.arguments(exception.message);
            })
    }

    /**
     *
     * @param categoria
     */
    public insertCategoria(categoria): void
    {
        Broker.of("categoriaService").promise("insertCategoria", categoria).then((categoria) =>
        {
            this.openSnackBar("Categoria salva com sucesso!");
            this.router.navigate([""]);
        }).catch((exception) =>
        {
            this.openSnackBar(exception.message);
        })
    }

    /**
     *
     * @param categoria
     */
    public updateCategoria(categoria): void
    {
        if (this.categoriaForm.valid)
        {
            Broker.of("categoriaService").promise("updateCategoria", categoria).then((categoria) =>
            {
                this.openSnackBar("Categoria atualizada com sucesso!");
                this.router.navigate([""]);
            }).catch((exception) =>
            {
                this.openSnackBar.arguments(exception.message);
            })
        }
    }
}
