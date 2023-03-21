import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { removeCredentials, setCredentials } from "../slice/Auth.slice";
const baseUrl =
  "https://c9-20-javareact-fulltime-production.up.railway.app/api";

const baseQuery = fetchBaseQuery({
  baseUrl,
  credentials: "include", // TODO: check if this is needed AND WHY
  prepareHeaders: (headers, { getState }) => {
    const accessToken = getState().auth.accessToken;
    if (accessToken) {
      headers.set("authorization", `Bearer ${accessToken}`);
      console.log("entra");
    }
    return headers;
  },
});

const baseQueryWithReauth = async (args, api, extraOptions) => {
  let response = await baseQuery(args, api, extraOptions);

  if (response.error) {
    const { status } = response.error;
    if (status === 401) {
      console.log("Sending refersh Token");
      // sending refresh token to get new access Token
      const getRefreshTokenResponse = await baseQuery(
        "/refresh-token",
        api,
        extraOptions
      );
      console.log(getRefreshTokenResponse, "  REFRESH TOKEN");

      if (getRefreshTokenResponse?.data) {
        const userType = api.getState().authSlice.userType;
        //store new token
        api.dispatch(
          setCredentials({ ...getRefreshTokenResponse.data, userType })
        );
        // retry the original request with new access token
        response = await baseQuery(args, api, extraOptions);
      } else {
        api.dispatch(removeCredentials());
      }
    }
  }

  return response;
};

export const authQueries = createApi({
  baseQuery: baseQueryWithReauth,
  endpoints: (builder) => ({}),
});
