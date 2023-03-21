import { createSlice } from "@reduxjs/toolkit";
const initialState = { value: 0 };

export const AppetizerData = createSlice({
	name: "AppetizerData",
	initialState,
	reducers: {
		increment: (state) => {
			return {
				...state,
				value: state.value + 1,
			};
		},
		decrement: (state) => {
			if (state.value <= 0) {
				state.value = 0;
				return {
					...state,
					value: 0,
				};
			}

			return {
				...state,
				value: state.value - 1,
			};
		},

		resetValue: (state) => {
			state.value = 0;
		},
	},
});

export const { increment, decrement, resetValue } = AppetizerData.actions;
export default AppetizerData.reducer;
