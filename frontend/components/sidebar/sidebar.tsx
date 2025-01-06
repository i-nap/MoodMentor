"use client";
import React, { useState } from "react";
import { Sidebar, SidebarBody, SidebarLink } from "../ui/sidebar";
import {
  IconArrowLeft,
  IconSettings,
} from "@tabler/icons-react";
import Link from "next/link";
import { motion } from "framer-motion";
import Image from "next/image";
import { cn } from "@/lib/utils";
import { Menu, MessageCircle, MessageCircleHeartIcon, MessageCircleMoreIcon, PlusCircle, SendHorizonal } from "lucide-react";
import { Button } from "../ui/button";
import { Input } from "../ui/input";
import { InputNormal } from "../ui/inputnormal";
import { Textarea } from "../ui/textarea";
import ChatBox from "../chatbox/chatbox";

export function SidebarMain() {
  const recentChats = [
    { id: 1, title: "Chat with John", href: "#" },
    { id: 2, title: "Project Discussion", href: "#" },
    { id: 3, title: "Brainstorming Ideas", href: "#" },
  ];

  const [open, setOpen] = useState(false);

  return (
    <div
      className={cn(
        "rounded-md flex flex-col md:flex-row bg-gray-100 dark:bg-neutral-800 w-full flex-1 mx-auto border border-neutral-200 dark:border-neutral-700 overflow-hidden",
        "h-screen w-full pl-2"
      )}
    >
      <Sidebar open={open} setOpen={setOpen} >
        <SidebarBody className="justify-between ">
          <div className="flex flex-col flex-1 overflow-y-auto overflow-x-hidden">
          <SidebarLink
                link={{
                  label: "",
                  href: "#",
                  icon: (
                    <Menu/>
                  ),
                }}
              />

            <div className="mt-20 flex flex-col gap-2">
              {/* Add Chat Button */}
              <SidebarLink
                link={{
                  label: "New Chat",
                  href: "#",
                  icon: (
                    <PlusCircle />
                  ),
                }}
              />

              {/* Recent Chats */}
              {open && (
                <motion.div
                  initial={{ opacity: 0, y: -10 }}
                  animate={{ opacity: 1, y: 0 }}
                  exit={{ opacity: 0, y: -10 }}
                  transition={{ duration: 0.3 }}
                  className="mt-4 flex flex-col"
                >
                  <h3 className="text-lg font-medium text-black dark:text-white mb-4 text-center">
                    Recent Chats
                  </h3>
                  <ul className="">
                    {recentChats.map((chat) => (
                      <li key={chat.id}>
                        <SidebarLink
                          link={{
                            label: chat.title, // Use the chat title dynamically
                            href: chat.href, // Use the chat href dynamically
                            icon: <MessageCircleMoreIcon />, // Reuse the icon
                          }}
                        />
                      </li>
                    ))}
                  </ul>
                </motion.div>
              )}

            </div>
          </div>
          <div className="flex flex-col gap-2 ">

            <SidebarLink
              link={{
                label: "Settings",
                href: "#",
                icon: (
                  <IconSettings />
                ),
              }}
            />



          </div>
        </SidebarBody>
      </Sidebar>
      <ChatBox />
    </div>
  );
}


export const LogoIcon = () => {
  return (
    <Link
      href="#"
      className="font-normal flex space-x-2 items-center text-sm text-black py-1 relative z-20"
    >
      <div className="h-5 w-6 bg-black dark:bg-white rounded-br-lg rounded-tr-sm rounded-tl-lg rounded-bl-sm flex-shrink-0" />
    </Link>
  );
};


