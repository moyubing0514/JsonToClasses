//
//  LoginRequest.m
//
//  Created by JsonTool 
//  24 Aug 2014 12:44:23 GMT
//  Copyright (c) 2014 YudiMo. All rights reserved.
//
#import <Foundation/Foundation.h>
#import "Message.h"

/**
 *
 *MESSAGE LoginRequest
 *example
 *{
 *{msgName:32131,
 *sessionKey:32131,
 *uuId:32131,
 *
 *}
**/

@implementation LoginRequest

@synthesize msgName = _msgName;
@synthesize sessionKey = _sessionKey;
@synthesize uuId = _uuId;



- (id)init
{
    return [super init];
}

//json字典赋值
-(id)initWithJson:(NSDictionary*)json
{
    self = [self init];
    self.msgName = [json objectForKey:@"msgName"];
	self.sessionKey = [json objectForKey:@"sessionKey"];
	self.uuId = [json objectForKey:@"uuId"];
	
    return self;
}
-(NSString*)toJsonString
{
    NSDictionary *dic = [NSDictionary dictionaryWithObjectsAndKeys:
                       	_msgName,@"msgName",
	_sessionKey,@"sessionKey",
	_uuId,@"uuId",

                        nil ];
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&error];
    NSString *json =[[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    return json;
}
@end

/**
 *
 *MESSAGE LoginNotify
 *example
 *{
 *{msgName:32131,
 *ret:32131,
 *
 *}
**/

@implementation LoginNotify

@synthesize msgName = _msgName;
@synthesize ret = _ret;



- (id)init
{
    return [super init];
}

//json字典赋值
-(id)initWithJson:(NSDictionary*)json
{
    self = [self init];
    self.msgName = [json objectForKey:@"msgName"];
	self.ret = [[[NSNumberFormatter alloc] init] numberFromString:[json objectForKey:@"ret"]];
	
    return self;
}
-(NSString*)toJsonString
{
    NSDictionary *dic = [NSDictionary dictionaryWithObjectsAndKeys:
                       	_msgName,@"msgName",
	_ret,@"ret",

                        nil ];
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&error];
    NSString *json =[[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    return json;
}
@end

