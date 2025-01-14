"use client";
import React, { useState } from "react";
import ReactDOM from "react-dom";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { LogIn, UserCircle, UserPlusIcon } from "lucide-react";
import { SignupForm } from "../forms/signup";
import { LoginForm } from "../forms/login";

export default function NotLoggedIn() {
  const [isSignupVisible, setSignupVisible] = useState(false);
  const [isLoginVisible, setLoginVisible] = useState(false);

  const handleSignupClick = () => {
    setSignupVisible(true);
  };

  const handleLoginClick = () => {
    setLoginVisible(true);
  };

  const handleCloseSignup = () => {
    setSignupVisible(false);
  };

  const handleCloseLogin = () => {
    setLoginVisible(false);
  };

  const handleSignupSuccess = () => {
    setSignupVisible(false); // Close the signup modal
    setLoginVisible(true); // Open the login modal
  };

  const signupModal = isSignupVisible ? (
    <div className="fixed inset-0 z-50 flex items-center justify-center">
      {/* Backdrop */}
      <div
        className="absolute inset-0 bg-black opacity-50"
        onClick={handleCloseSignup}
      />
      {/* Signup Form */}
      <div className="relative bg-white dark:bg-black rounded-lg shadow-lg w-full max-w-md">
        {/* Close Button */}
        <button
          className="absolute top-3 right-3 text-neutral-500 hover:text-neutral-800 dark:text-neutral-400 dark:hover:text-neutral-200"
          onClick={handleCloseSignup}
        >
          ✕
        </button>
        <SignupForm onSignupSuccess={handleSignupSuccess} />
      </div>
    </div>
  ) : null;

  const loginModal = isLoginVisible ? (
    <div className="fixed inset-0 z-50 flex items-center justify-center">
      {/* Backdrop */}
      <div
        className="absolute inset-0 bg-black opacity-50"
        onClick={handleCloseLogin}
      />
      {/* Login Form */}
      <div className="relative bg-white dark:bg-black rounded-lg shadow-lg w-full max-w-md">
        {/* Close Button */}
        <button
          className="absolute top-3 right-3 text-neutral-500 hover:text-neutral-800 dark:text-neutral-400 dark:hover:text-neutral-200"
          onClick={handleCloseLogin}
        >
          ✕
        </button>
        <LoginForm />
      </div>
    </div>
  ) : null;

  return (
    <>
      <DropdownMenu>
        <DropdownMenuTrigger asChild className="cursor-pointer">
          <UserCircle className="w-5 h-5" />
        </DropdownMenuTrigger>
        <DropdownMenuContent className="w-56">
          <DropdownMenuLabel>My Account</DropdownMenuLabel>
          <DropdownMenuSeparator />
          <DropdownMenuGroup>
            <DropdownMenuItem onClick={handleLoginClick}>
              <LogIn />
              <span>Login</span>
            </DropdownMenuItem>
            <DropdownMenuItem onClick={handleSignupClick}>
              <UserPlusIcon />
              <span>Sign up</span>
            </DropdownMenuItem>
          </DropdownMenuGroup>
        </DropdownMenuContent>
      </DropdownMenu>

      {isSignupVisible && ReactDOM.createPortal(signupModal, document.body)}
      {isLoginVisible && ReactDOM.createPortal(loginModal, document.body)}
    </>
  );
}
