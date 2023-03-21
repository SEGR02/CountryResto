import { useState } from "react";
import InfoCheckout from "../model/InfoCheckout.model";

const useConfirmation = () => {
	const state = new InfoCheckout();

	const [stateCheckout, setStateCheckout] = useState(state);
	const [shipment, setShipment] = useState(false);
	const handleChange = (event) => {
		const { name, value } = event.target;
		if (name === "shipment") {
			if (value === "delivery") {
				setShipment(true);
			} else if (value === "local") {
				setShipment(false);
			}
		}
	};

	const onSubmit = (event) => {
		console.log(event);
	};

	return {
		onSubmit,
		handleChange,
		shipment,
	};
};

export default useConfirmation;
