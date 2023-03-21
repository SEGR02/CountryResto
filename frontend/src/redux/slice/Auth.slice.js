import { createSlice } from "@reduxjs/toolkit";
import Auth from "../../model/auth.model";

// const initialState = new Auth();
const initialState = {
	role: window.localStorage.getItem("role") || "",
	accessToken: window.localStorage.getItem("accessToken") || "",
	refreshToken: window.localStorage.getItem("refreshToken") || "",
};

export const authSlice = createSlice({
	name: "authSlice",
	initialState,
	reducers: {
		setCredentials: (state, action) => {
			const { role, accessToken, refreshToken } = action.payload;
			state.role = role;
			state.accessToken = accessToken;
			state.refreshToken = refreshToken;
		},
		removeCredentials: (state, action) => {
			console.log("removeCredentials");
			return {
				...initialState,
			};
		},
	},
});

export const { setCredentials, removeCredentials } = authSlice.actions;

export const selectCurrentUser = (state) => state.authSlice.userType;
export const selectAccessToken = (state) => state.authSlice.accessToken;
export const selectRefreshToken = (state) => state.authSlice.refreshToken;

export default authSlice.reducer;
