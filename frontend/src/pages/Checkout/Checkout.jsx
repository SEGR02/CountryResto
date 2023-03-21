import { useState } from "react";
import { useForm } from "react-hook-form";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { CardList, Header } from "../../components";
import { DivCol } from "../../styled-components/layout/layout.styled";
import {
  Button,
  CheckCart,
  CheckContainer,
  CheckData,
  ContainerFoodStyled,
  ContainerInputsStyled,
  ContainerStyled,
  ContainerTotalStyled,
  FieldsetStyled,
  Unwrapped,
} from "./CheckoutStyled.jsx";
import Checked from "./components/Checked";
import Input from "./components/Input";
import SubTitle from "./components/SubTitle";
import usePayment from "./hook/usePayment";
const Checkout = () => {
  const cart = useSelector((state) => state.cart.items);
  const total = useSelector((state) => state.cart.total);
  const userAddress = useSelector((state) => state.userData.userAddress);
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const { handleChange, payment, handleAddPayment } = usePayment();
  const [selectedPaymentOption, setSelectedPaymentOption] = useState("debito");

  /* const { handleChange, shipment } = useConfirmation(); */
  const onSubmit = (data) => {
    const newState = Object.assign({}, payment, data);

    handleAddPayment(newState);
    navigate("/thanks");
  };

  return (
    <>
      <Header />
      <CardList />
      <CheckContainer>
        <CheckData onSubmit={handleSubmit(onSubmit)}>
          <SubTitle title="Envío" bottom={"-26%"} />
          <ContainerStyled>
            <Checked
              checked="local"
              id="local"
              labelText="Retiro en local"
              name="holder"
              value="local"
              onChange={handleChange}
            />
            <Checked
              id="delivery"
              labelText="Delivery"
              name="holder"
              value="delivery"
              onChange={handleChange}
            />
          </ContainerStyled>
          <SubTitle title="Pago" bottom={"-26%"} />
          <FieldsetStyled>
            <legend>Método de pago</legend>
            <Checked
              checked={"debito"}
              id="debito"
              labelText="Débito"
              name="type"
              value="DEBIT"
              onChange={handleChange}
            />
            <Checked
              id="credito"
              labelText="Crédito"
              name="type"
              value="CREDIT"
              onChange={handleChange}
            />
          </FieldsetStyled>

          {selectedPaymentOption === "debito" && (
            <>
              <ContainerInputsStyled>
                <Input
                  textLabel="Número de tarjeta"
                  type="text"
                  name="cardNumber"
                  placeholder="1234 5678 9101 1121"
                  inlineSize="476px"
                  bottom="-23%"
                  register={{
                    ...register("number", {
                      required: "Campo requerido",
                      pattern: {
                        value: /^[0-9]{1,16}$/,
                        message:
                          "Este campo solo acepta 16 caracteres numéricos",
                      },
                    }),
                  }}
                  error={errors.number}
                />

                <Input
                  textLabel="Fecha de vencimiento"
                  type="date"
                  name="dueDate"
                  placeholder="MM/YY"
                  inlineSize="233px"
                  bottom="-23%"
                  register={{
                    ...register("expirationDate", {
                      required: "Campo requerido",
                    }),
                  }}
                  error={errors.expirationDate}
                />

                <Input
                  textLabel="CVV"
                  type="password"
                  name="cvv"
                  placeholder="Ingrese su CVV"
                  inlineSize="233px"
                  bottom="-22%"
                  register={{
                    ...register("cvv", {
                      required: "Campo requerido",
                      pattern: {
                        value: /^[0-9]{1,3}$/,
                        message:
                          "Este campo solo acepta 3 caracteres numéricos",
                      },
                    }),
                  }}
                  error={errors.cvv}
                />
              </ContainerInputsStyled>
              <Button>Confirmar</Button>
            </>
          )}
          {selectedPaymentOption === "credito" && (
            <>
              <Unwrapped>
                <select>
                  <option>Seleccionar Tarjeta</option>
                  <option>VISA</option>
                  <option>MASTERCARD</option>
                </select>
              </Unwrapped>
              <div>
                <h3>Cuotas</h3>
                <input type="radio" name="cuotas" checked />
                <label htmlFor="">1 Cuota</label>
                <input type="radio" name="cuotas" />
                <label htmlFor="">3 Cuotas</label>
                <input type="radio" name="cuotas" />
                <label htmlFor="">6 Cuotas</label>
              </div>
              <Button>Confirmar</Button>
            </>
          )}
        </CheckData>
        <CheckCart>
          <SubTitle title="Resumen" bottom={"0%"} />
          <div>
            <DivCol>
              {cart.length !== 0 ? (
                cart.map((cr) => (
                  <ContainerFoodStyled key={cr.id} bottom="-35%">
                    <strong>
                      <span>{cr.amount}</span>X<span>{cr.name}</span>
                    </strong>
                    <p>${cr.subTotal}</p>
                  </ContainerFoodStyled>
                ))
              ) : (
                <span>Sin Productos</span>
              )}

              <ContainerTotalStyled>
                <div>
                  <b>Subtotal:</b> <b>${total}</b>
                </div>
                <div>
                  <b>Envio </b>
                  <span>{payment.holder === "delivery" ? "$150" : "-"}</span>
                </div>
                <div>
                  <div>
                    <b>Total:</b>
                    <b>
                      ${payment.holder === "delivery" ? total + 150 : total}
                    </b>
                  </div>
                  <span>
                    {payment.holder
                      ? userAddress
                      : "Retiro en local: Urquiza 2345 - Capital"}
                  </span>
                </div>
              </ContainerTotalStyled>
            </DivCol>
          </div>
        </CheckCart>
      </CheckContainer>
    </>
  );
};

export default Checkout;
