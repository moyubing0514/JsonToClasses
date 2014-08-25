//
//  LoginRequest.h
//
//  Created by JsonTool 
//  24 Aug 2014 12:44:23 GMT
//  Copyright (c) 2014 YudiMo. All rights reserved.
//
#import <Foundation/Foundation.h>
#import "Message.h"

@interface LoginRequest : NSObject
{
	NSString* _msgName;
	NSString* _sessionKey;
	NSString* _uuId;
	
}

-(id)init;
-(id)initWithJson:(NSDictionary*)json;
@property (copy)NSString* msgName;
@property (copy)NSString* sessionKey;
@property (copy)NSString* uuId;

@end

@interface LoginNotify : NSObject
{
	NSString* _msgName;
	NSNumber* _ret;
	
}

-(id)init;
-(id)initWithJson:(NSDictionary*)json;
@property (copy)NSString* msgName;
@property (copy)NSNumber* ret;

@end

