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
import { LogIn, UserPlusIcon } from "lucide-react";
import { SignupForm } from "../forms/signup";

export default function NotLoggedIn() {
  const [isSignupVisible, setSignupVisible] = useState(false);

  const handleSignupClick = () => {
    setSignupVisible(true);
  };

  const handleClose = () => {
    setSignupVisible(false);
  };

  const signupModal = isSignupVisible ? (
    <div className="fixed inset-0 z-50 flex items-center justify-center">
      {/* Backdrop */}
      <div
        className="absolute inset-0 bg-black opacity-50"
        onClick={handleClose}
      />
      {/* Signup Form */}
      <div className="relative bg-white dark:bg-black rounded-lg shadow-lg w-full max-w-md">
        {/* Close Button */}
        <button
          className="absolute top-3 right-3 text-neutral-500 hover:text-neutral-800 dark:text-neutral-400 dark:hover:text-neutral-200"
          onClick={handleClose}
        >
          ✕
        </button>
        <SignupForm />
      </div>
    </div>
  ) : null;

  return (
    <>
      <DropdownMenu>
        <DropdownMenuTrigger asChild>
          <div className="rounded-full bg-neutral-400 w-8 h-8" />
        </DropdownMenuTrigger>
        <DropdownMenuContent className="w-56">
          <DropdownMenuLabel>My Account</DropdownMenuLabel>
          <DropdownMenuSeparator />
          <DropdownMenuGroup>
            <DropdownMenuItem>
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

      {isSignupVisible &&
        ReactDOM.createPortal(signupModal, document.body)}
    </>
  );
}
