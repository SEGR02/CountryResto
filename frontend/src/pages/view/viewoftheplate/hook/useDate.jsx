import { useEffect, useState } from "react";

const useDate = () => {
	const [date, setDate] = useState(new Date());

	const months = [
		"Enero",
		"Febrero",
		"Marzo",
		"Abril",
		"Mayo",
		"Junio",
		"Julio",
		"Agosto",
		"Septiembre",
		"Octubre",
		"Noviembre",
		"Diciembre",
	];

	const newDate = {
		day: date.getDate(),
		year: date.getFullYear(),
		minutes: date.getMinutes(),
		hour: date.getHours(),
		months: months[date.getMonth()],
	};

	const [infoDate, setInfoDate] = useState(newDate);

	useEffect(() => {
		setDate(new Date());
	}, []);

	return {
		infoDate,
	};
};

export default useDate;
