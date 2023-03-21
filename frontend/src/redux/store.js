import { configureStore } from "@reduxjs/toolkit";
import { FoodInfoApi } from "./query/FoodInfo.query";
import { authQueries } from "./query/wrapper.query.js";
import AppetizerDataSlice from "./slice/AppetizerData.slice";
import authSlice from "./slice/Auth.slice";
import CartSlice from "./slice/cart.slice";
import UserDataSlice from "./slice/UserData.slice";

export default configureStore({
	reducer: {
		auth: authSlice,
		AppetizerData: AppetizerDataSlice,
		cart: CartSlice,
		userData: UserDataSlice,
		[FoodInfoApi.reducerPath]: FoodInfoApi.reducer,
		[authQueries.reducerPath]: authQueries.reducer,
	},
	middleware: (getDefaultMiddleware) =>
		getDefaultMiddleware({ serializableCheck: false }).concat(
			FoodInfoApi.middleware,
			authQueries.middleware,
		),
});
