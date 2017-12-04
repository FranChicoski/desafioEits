//===============================ANGULAR MODULES=================================
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';


import {
  MdAutocompleteModule,
  MdButtonModule,
  MdButtonToggleModule,
  MdCardModule,
  MdCheckboxModule,
  MdDatepickerModule,
  MdDialogModule,
  MdIconModule,
  MdInputModule,
  MdListModule,
  MdMenuModule,
  MdNativeDateModule,
  MdProgressSpinnerModule,
  MdRadioModule,
  MdSelectModule,
  MdSidenavModule,
  MdSlideToggleModule,
  MdSnackBarModule,
  MdToolbarModule,
  MdTooltipModule,
  MdSnackBar
} from '@angular/material';

import { FormsModule } from '@angular/forms';
import { Http } from '@angular/http';
import { Validators, NgForm ,Form, FormBuilder ,FormControl, ReactiveFormsModule} from '@angular/forms';
import { Inject } from '@angular/core';
import { Md2DatepickerModule } from 'md2/datepicker';

//===============================COVALENT MODULES================================
import {
  CovalentChipsModule,
  CovalentCommonModule,
  CovalentDataTableModule,
  CovalentDialogsModule,
  CovalentExpansionPanelModule,
  CovalentFileModule,
  CovalentLayoutModule,
  CovalentLoadingModule,
  CovalentMediaModule,
  CovalentMessageModule,
  CovalentPagingModule,
  CovalentStepsModule,
  TdDialogService,
  TdLayoutComponent,
  ITdDataTableColumn
} from '@covalent/core';
//===============================APP MODULES=====================================
import { appRoutingProviders, RoutingModule } from './routing.module';
import 'rxjs/add/observable/throw';

import { ExcluirConfirmComponent } from './controls/excluir/excluir-confirm.component';
import { DetailCategoriaComponent } from './views/categoria/detail/detail.component';
import { CategoriaViewComponent } from './views/categoria/categoria-view.component';
import { ListCategoriaComponent } from './views/categoria/list/list.component';
import { FormComponent } from './views/categoria/form/form.component';
import { RouterModule } from '@angular/router/src/router_module';

//==============================APP SERVICES=====================================
//==============================APP COMPONENTS===================================

//=============================APP DIRECTIVES====================================

//===============================APP MODELS======================================

/**
 *
 */
@NgModule({
  declarations: [
    CategoriaViewComponent,
    ListCategoriaComponent,
    FormComponent,
    DetailCategoriaComponent,
    ExcluirConfirmComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    ReactiveFormsModule,
    CovalentLayoutModule,
    CovalentStepsModule,
    CovalentChipsModule,
    CovalentFileModule,
    CovalentExpansionPanelModule,
    CovalentPagingModule,
    CovalentLoadingModule,
    CovalentMediaModule,
    CovalentMessageModule,
    CovalentCommonModule,
    CovalentDataTableModule,
    CovalentDialogsModule,
    MdAutocompleteModule,
    MdIconModule,
    MdSelectModule,
    MdSlideToggleModule,
    MdMenuModule,
    MdInputModule,
    MdCheckboxModule,
    MdRadioModule,
    MdSidenavModule,
    MdSnackBarModule,
    MdDialogModule,
    MdCardModule,
    MdButtonModule,
    MdToolbarModule,
    MdListModule,
    MdDatepickerModule,
    MdNativeDateModule,
    MdTooltipModule,
    MdProgressSpinnerModule,
    MdButtonToggleModule,
    FormsModule,
    RoutingModule,
    Md2DatepickerModule,
    MdDatepickerModule
  ],
  exports: [
    BrowserModule
  ],
  providers: [
    appRoutingProviders,
    TdLayoutComponent,
    TdDialogService,
  ],
  entryComponents:[
    ExcluirConfirmComponent
  ],
  bootstrap: [CategoriaViewComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class Module {

}
