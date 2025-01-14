import {
    InputOTP,
    InputOTPGroup,
    InputOTPSeparator,
    InputOTPSlot,
  } from "@/components/ui/input-otp"
  
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
      <InputOTP maxLength={6}>
        <InputOTPGroup>
          <InputOTPSlot index={0} />
          <InputOTPSlot index={1} />
          <InputOTPSlot index={2} />
        </InputOTPGroup>
        <InputOTPSeparator />
        <InputOTPGroup>
          <InputOTPSlot index={3} />
          <InputOTPSlot index={4} />
          <InputOTPSlot index={5} />
        </InputOTPGroup>
      </InputOTP>
    )
  }
  