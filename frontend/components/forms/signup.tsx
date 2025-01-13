"use client";
import React, { useState } from "react";
import { Label } from "../ui/label";
import { Input } from "../ui/input";
import { cn } from "@/lib/utils";
import { IconBrandGoogle } from '@tabler/icons-react';
import { Separator } from "@/components/ui/separator"
import { ArrowLeft } from "lucide-react";
import {
    Select,
    SelectContent,
    SelectGroup,
    SelectItem,
    SelectLabel,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select"
import { OTPInput } from "input-otp";
import { InputOTPLogin } from "./otp";
export function SignupForm() {
    const [step, setStep] = useState(1);
    const [formData, setFormData] = useState({
        email: "",
        firstname: "",
        middlename: "",
        lastname: "",
        age: "",
        gender: "",
        password: "",
        cpassword: "",
        otp: ""
    });

    const handleInputChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
    ) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleNextStep = () => {
        setStep((prev) => prev + 1);
    };

    const handlePreviousStep = () => {
        setStep((prev) => prev - 1);
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log("Form submitted", formData);
    };

    return (
        <div className="max-w-md w-full mx-auto rounded-none md:rounded-2xl p-4 md:p-8 shadow-input bg-white dark:bg-black">
            <h2 className="font-bold text-xl text-neutral-800 dark:text-neutral-200">
                {step === 1 ? "Welcome to Mood Mentor" : step === 2 ? "Verify OTP" : "Complete Your Profile"}
            </h2>
            <p className="text-neutral-600 text-sm max-w-sm mt-2 dark:text-neutral-300">
                {step === 1
                    ? "Enter your email to continue"
                    : step === 2
                        ? "Enter the OTP sent to your email"
                        : "Fill in the remaining details"}
            </p>

            <form className="my-8" onSubmit={handleSubmit}>
                {/* Step 1: Email */}
                {step === 1 && (
                    <>
                        <button
                            className=" relative group/btn flex space-x-2 items-center justify-start px-4 w-full text-black rounded-md h-10 font-medium shadow-input bg-gray-50 dark:bg-zinc-900 dark:shadow-[0px_0px_1px_1px_var(--neutral-800)]"
                            type="button"
                            onClick={handleNextStep}
                        >
                            <IconBrandGoogle className="h-4 w-4 text-neutral-800 dark:text-neutral-300" />
                            <span className="text-neutral-700 dark:text-neutral-300 text-sm">
                                Signup with Google
                            </span>
                            <BottomGradient />
                        </button>
                        <Separator className="my-4" />
                        <LabelInputContainer className="mb-4">
                            <Label htmlFor="email">Email Address</Label>
                            <Input
                                id="email"
                                name="email"
                                placeholder="example@domain.com"
                                type="email"
                                value={formData.email}
                                onChange={handleInputChange}
                                required
                            />
                        </LabelInputContainer>
                        <button
                            type="button"
                            className="bg-gradient-to-br relative group/btn from-black dark:from-zinc-900 dark:to-zinc-900 to-neutral-600 block dark:bg-zinc-800 w-full text-white rounded-md h-10 font-medium shadow-[0px_1px_0px_0px_#ffffff40_inset,0px_-1px_0px_0px_#ffffff40_inset] dark:shadow-[0px_1px_0px_0px_var(--zinc-800)_inset,0px_-1px_0px_0px_var(--zinc-800)_inset] mt-6"
                            onClick={handleNextStep}
                        >
                            Proceed
                            <BottomGradient />
                        </button>
                    </>
                )}

                {/* Step 2: OTP Verification */}
                {step === 2 && (
                    <>

                        <div className="gap-4 flex flex-col items-center">
                            <InputOTPLogin />
                        </div>



                    </>
                )}

                {/* Step 3: Additional Fields */}
                {step === 3 && (
                    <>
                        <div className="flex flex-col md:flex-row space-y-2 md:space-y-0 md:space-x-2 mb-4">

                            <LabelInputContainer>
                                <Label htmlFor="firstname">First Name</Label>
                                <Input
                                    id="firstname"
                                    name="firstname"
                                    placeholder="John"
                                    type="text"
                                    value={formData.firstname}
                                    onChange={handleInputChange}
                                    required
                                />
                            </LabelInputContainer>
                            <LabelInputContainer>
                                <Label htmlFor="middlename">Middle Name (optional)</Label>
                                <Input
                                    id="middlename"
                                    name="middlename"
                                    placeholder="Michael"
                                    type="text"
                                    value={formData.middlename}
                                    onChange={handleInputChange}
                                />
                            </LabelInputContainer>
                        </div>
                 

                        <div className="flex flex-col md:flex-row space-y-2 md:space-y-0 md:space-x-2 mb-4">
                            <LabelInputContainer>
                                <Label htmlFor="lastname">Last Name</Label>
                                <Input
                                    id="lastname"
                                    name="lastname"
                                    placeholder="Doe"
                                    type="text"
                                    value={formData.lastname}
                                    onChange={handleInputChange}
                                    required
                                />
                            </LabelInputContainer>
                            <LabelInputContainer >
                                <Label htmlFor="age">Age</Label>
                                <Input
                                    id="age"
                                    name="age"
                                    placeholder="30"
                                    type="number"
                                    value={formData.age}
                                    onChange={handleInputChange}
                                    required
                                />
                            </LabelInputContainer>
                        </div>
                        <LabelInputContainer className="mb-4">
                            <Label htmlFor="gender">Gender</Label>
                            <Select value={formData.gender} onValueChange={(value) => {
                                setFormData((prev) => ({
                                    ...prev,
                                    gender: value,
                                }));
                            }}>
                                <SelectTrigger className="w-full border rounded-md p-2 border-none bg-gray-50 dark:bg-zinc-800 text-black dark:text-white shadow-input bg-gray-50 dark:bg-zinc-800 text-black dark:text-white shadow-input rounded-md px-3 py-2 text-sm  file:border-0 file:bg-transparent 
          file:text-sm file:font-medium placeholder:text-neutral-400 dark:placeholder-text-neutral-600 
          focus-visible:outline-none focus-visible:ring-[2px]  focus-visible:ring-neutral-400 dark:focus-visible:ring-neutral-600
           disabled:cursor-not-allowed disabled:opacity-50
           dark:shadow-[0px_0px_1px_1px_var(--neutral-700)]
           group-hover/input:shadow-none transition duration-400" >
                                    <SelectValue />
                                </SelectTrigger>
                                <SelectContent>

                                    <SelectItem value="male">Male</SelectItem>
                                    <SelectItem value="female">Female</SelectItem>
                                    <SelectItem value="other">Other</SelectItem>

                                </SelectContent>
                            </Select>
                        </LabelInputContainer>
                        <LabelInputContainer className="mb-4">
                            <Label htmlFor="password">Password</Label>
                            <Input
                                id="password"
                                name="password"
                                placeholder="••••••••"
                                type="password"
                                value={formData.password}
                                onChange={handleInputChange}
                                required
                            />
                        </LabelInputContainer>
                        <LabelInputContainer>
                            <Label htmlFor="cpassword">Confirm Password</Label>
                            <Input
                                id="cpassword"
                                name="cpassword"
                                placeholder="••••••••"
                                type="password"
                                value={formData.cpassword}
                                onChange={handleInputChange}
                                required
                            />
                        </LabelInputContainer>
                    </>
                )}

                {/* Navigation Buttons */}
                <div className="flex justify-between space-x-5 mt-6">
                    {step > 1 && (
                        <button
                            type="button"
                            className="flex items-center justify-center gap-2 bg-neutral-200 dark:bg-neutral-700 text-neutral-700 dark:text-white hover:bg-neutral-300 dark:hover:bg-neutral-500 px-4 py-2 rounded-md font-medium shadow-md transition-colors duration-200"
                            onClick={handlePreviousStep}
                        >
                            <ArrowLeft size={16} />
                            Back
                            <BottomGradient />
                        </button>
                    )}
                    {step === 2 && (
                        <button
                            type="submit"
                            className="bg-gradient-to-br relative group/btn from-black dark:from-zinc-900 dark:to-zinc-900 to-neutral-600 block dark:bg-zinc-800 w-full text-white rounded-md h-10 font-medium shadow-[0px_1px_0px_0px_#ffffff40_inset,0px_-1px_0px_0px_#ffffff40_inset] dark:shadow-[0px_1px_0px_0px_var(--zinc-800)_inset,0px_-1px_0px_0px_var(--zinc-800)_inset]"

                            onClick={handleNextStep}
                        >
                            Verify OTP
                            <BottomGradient />
                        </button>
                    )}
                    {step === 3 && (<button
                        type="submit"
                        className="bg-gradient-to-br relative group/btn from-black dark:from-zinc-900 dark:to-zinc-900 to-neutral-600 block dark:bg-zinc-800 w-full text-white rounded-md h-10 font-medium shadow-[0px_1px_0px_0px_#ffffff40_inset,0px_-1px_0px_0px_#ffffff40_inset] dark:shadow-[0px_1px_0px_0px_var(--zinc-800)_inset,0px_-1px_0px_0px_var(--zinc-800)_inset]"
                    >
                        Sign up
                        <BottomGradient />
                    </button>

                    )}
                </div>
            </form>
        </div>
    );
}

const BottomGradient = () => {
    return (
        <>
            <span className="group-hover/btn:opacity-100 block transition duration-500 opacity-0 absolute h-px w-full -bottom-px inset-x-0 bg-gradient-to-r from-transparent via-cyan-500 to-transparent" />
            <span className="group-hover/btn:opacity-100 blur-sm block transition duration-500 opacity-0 absolute h-px w-1/2 mx-auto -bottom-px inset-x-10 bg-gradient-to-r from-transparent via-indigo-500 to-transparent" />
        </>
    );
};

const LabelInputContainer = ({
    children,
    className,
}: {
    children: React.ReactNode;
    className?: string;
}) => {
    return (
        <div className={cn("flex flex-col space-y-2 w-full", className)}>
            {children}
        </div>
    );
};
