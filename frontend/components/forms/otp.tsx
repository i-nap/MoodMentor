import React, { useState } from "react";
import axios from "axios";
import {
  InputOTP,
  InputOTPGroup,
  InputOTPSeparator,
  InputOTPSlot,
} from "@/components/ui/input-otp";

export function InputOTPLogin({ email }) {
  const [otp, setOTP] = useState(["", "", "", "", "", ""]);
  const [message, setMessage] = useState("");

  // Function to validate OTP
  const validateOTP = async () => {
    try {
      const response = await axios.post("/otp/validate", {
        email,
        otp: otp.join(""),
      });
      setMessage(response.data);
    } catch (error) {
      setMessage(error.response?.data || "Error validating OTP");
    }
  };

  // Handle OTP input changes
  const handleOTPChange = (index, value) => {
    const updatedOTP = [...otp];
    updatedOTP[index] = value;
    setOTP(updatedOTP);
  };

  return (
    <div style={{ maxWidth: "400px", margin: "0 auto", textAlign: "center" }}>
      <h2>Verify OTP</h2>

      {/* OTP Input */}
      <InputOTP maxLength={6}>
        <InputOTPGroup>
          {[0, 1, 2].map((index) => (
            <InputOTPSlot
              key={index}
              index={index}
              value={otp[index]}
              onChange={(e) => handleOTPChange(index, e.target.value)}
            />
          ))}
        </InputOTPGroup>
        <InputOTPSeparator />
        <InputOTPGroup>
          {[3, 4, 5].map((index) => (
            <InputOTPSlot
              key={index}
              index={index}
              value={otp[index]}
              onChange={(e) => handleOTPChange(index, e.target.value)}
            />
          ))}
        </InputOTPGroup>
      </InputOTP>

      <button
        onClick={validateOTP}
        style={{
          marginTop: "20px",
          padding: "10px 20px",
          backgroundColor: "#28A745",
          color: "white",
          border: "none",
          borderRadius: "4px",
          cursor: "pointer",
        }}
      >
        Validate OTP
      </button>

      {/* Display Messages */}
      {message && (
        <p
          style={{
            marginTop: "20px",
            padding: "10px",
            backgroundColor: "#f8f9fa",
            border: "1px solid #dee2e6",
            borderRadius: "4px",
            color: "#212529",
          }}
        >
          {message}
        </p>
      )}
    </div>
  );
}
