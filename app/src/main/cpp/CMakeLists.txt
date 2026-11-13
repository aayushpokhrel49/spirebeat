cmake_minimum_required(VERSION 3.22.1)

project(spirebeat_discord_social)

add_library(spirebeat_discord_social SHARED discord_social_bridge.cpp)

target_compile_features(spirebeat_discord_social PRIVATE cxx_std_17)

target_link_libraries(spirebeat_discord_social android log)

if(SPIREBEAT_ENABLE_DISCORD_SOCIAL_SDK)
    find_package(discord_partner_sdk REQUIRED CONFIG)
    target_compile_definitions(spirebeat_discord_social PRIVATE SPIREBEAT_ENABLE_DISCORD_SOCIAL_SDK=1)
    target_link_libraries(spirebeat_discord_social discord_partner_sdk::discord_partner_sdk)
else()
    target_compile_definitions(spirebeat_discord_social PRIVATE SPIREBEAT_ENABLE_DISCORD_SOCIAL_SDK=0)
endif()
