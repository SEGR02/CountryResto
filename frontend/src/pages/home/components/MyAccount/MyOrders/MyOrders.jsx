import React from 'react'
import { MyOrdersStyle } from '../../../styled-components/MyAccountComponent'

export default function MisPedidos() {
    
    //Este array es momentaneo hasta poder consumir esta informacion de la API
    let infodeApi = ['1x Ñoquis Orden #123RGR231567Y Confirmado 1 de Jul. 2022 21:00Hs','1x Ñoquis Orden #123RGR231567Y Confirmado 1 de Jul. 2022 21:00Hs', '1x Ñoquis Orden #123RGR231567Y Confirmado 1 de Jul. 2022 21:00Hs']
  
    return (
    <MyOrdersStyle>
       <p className='titularPedidos'>Se muestran resultados de los pedidos realizados en los últimos 30 días</p>    
        { infodeApi.map(e=> <p className='descripcionPedidos'>{e}</p> )}
    </MyOrdersStyle>
  )
}
