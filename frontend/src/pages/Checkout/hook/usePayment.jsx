import { useState } from "react";
import { useSelector } from "react-redux";
import { useAddPaymentMutation } from "../../../redux/query/FoodInfo.query";
import InfoCheckout from "../model/InfoCheckout.model";
const usePayment = () => {
	const state = new InfoCheckout();
	const token = useSelector((state) => state.auth.accessToken);
	const [payment, setPayment] = useState(state);

	const handleChange = (event) => {
		const { name, value } = event.target;
		setPayment({
			...payment,
			[name]: value,
		});
	};
	const [addPayment] = useAddPaymentMutation();

	const handleAddPayment = async (data) => {
		const post = JSON.stringify(data);
		await addPayment(post, token).unwrap();
	};

	return {
		handleAddPayment,
		handleChange,
		payment,
	};
};

export default usePayment;
