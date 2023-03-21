import { createSlice } from "@reduxjs/toolkit";
const initialState = {
  userAddress: "",
};
export const UserDataSlice = createSlice({
  name: "userData",
  initialState,
  reducers: {
    setAddress: (state, { payload }) => {
      return {
        ...state,
        userAddress: payload,
      };
    },
  },
});

export const { setAddress } = UserDataSlice.actions;

export default UserDataSlice.reducer;
