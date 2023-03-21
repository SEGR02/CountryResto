import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const useDisplacement = () => {
  const { role } = useSelector((state) => state.auth);
  const navigate = useNavigate();

  useEffect(() => {
    if(!role) {
      navigate('/login');
    }
  }, [])

	return {
    role
	};
};

export default useDisplacement;
